package com.example.utils;


import com.example.dto.BaseGWRpcBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.lang.reflect.Method;
import java.util.*;


public class BeanToMapUtils {



    public static <T> T toBeanMap(Class<T> clazz, Map map,JSONObject jsonObject)  throws  Exception{


        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        T obj = clazz.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        Arrays.stream(propertyDescriptors).forEach(record -> {
            String propertyName = record.getName();//bean中对象的值
            //看是否存在映射
            if(jsonObject.containsKey(propertyName)){
                Object object = jsonObject.get(propertyName);

                if (object instanceof JSONObject){
                    propertyName = jsonObject.getJSONObject(propertyName).getString("key");
                }else{
                    propertyName = object.toString();
                }

            }

            if ( null != map && map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
                if ("".equals(value)) {
                    value = null;
                }
                Object[] args = new Object[1];
                args[0] = value;

                try {
                    if(null == record.getWriteMethod()){
                        return;
                    }

                    //如果类型是List对象
                    if(record.getPropertyType().getName().equals(List.class.getName())
                            || record.getPropertyType().getSuperclass().getClass().getName().equals(List.class.getName())){
                        //是List数据
                        List<Object> hmData =  (List)args[0];
                        List list = new ArrayList();

                        if(null == hmData){
                            record.getWriteMethod().invoke(obj,list );
                            return;
                        }

                        for (Object o : hmData) {
                            HashMap hmDataChild =  (HashMap)o;
                            Class classData = Class.forName(jsonObject.getJSONObject(record.getName()).getString("classname"));
                            Object aaa = toBean(classData.newInstance().getClass(),hmDataChild,new JSONObject());

                            list.add(aaa);
                        }

                        record.getWriteMethod().invoke(obj,list );

                        //如果是对象类型
                    }else if(record.getPropertyType().getName().equals(BaseGWRpcBean.class.getName())
                            || record.getPropertyType().getSuperclass().getClass().getName().equals(BaseGWRpcBean.class.getName())
                            ){
                        JSONObject jsonValue = jsonObject.getJSONObject(record.getName()).getJSONObject("value");
                        HashMap hmData =  (HashMap)args[0];
                        Class classData =Class.forName(jsonObject.getJSONObject(record.getName()).getString("classname"));
                        Object aaa = toBeanMap(classData.newInstance().getClass(),hmData,jsonValue);
                        record.getWriteMethod().invoke(obj,aaa);
                    }else if(null != args[0]){
                        record.getWriteMethod().invoke(obj, args);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }


        });

        return (T) obj;

    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param clazz 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */
    @SuppressWarnings("rawtypes")
    public static <T> T toBean(Class<T> clazz, Map map,JSONObject jsonObject)  throws  Exception{


            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            T obj = clazz.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            Arrays.stream(propertyDescriptors).forEach(record -> {

                try{


                    if(null == record.getWriteMethod()){
                        return;
                    }

                    //属性是数组类型
                    if(record.getPropertyType().getName().equals(List.class.getName())
                            ||
                            (record.getPropertyType().getSuperclass()!=null
                             &&
                             record.getPropertyType().getSuperclass().getClass().getName().equals(List.class.getName()))){


                        JSONObject object = jsonObject.getJSONObject(record.getName());

                        String key = object.getString("key");
                        Object objVal =getJsonValue(map,key);

                        //是List数据
                        List<Object> hmData =  (List)objVal;
                        List list = new ArrayList();

                        if(null == hmData){
                            record.getWriteMethod().invoke(obj,list );
                            return;
                        }


                        for (Object o : hmData) {
                            HashMap hmDataChild =  (HashMap)o;
                            Class classData = Class.forName(object.getString("classname"));
                            Object aaa = toBean(classData.newInstance().getClass(),hmDataChild,object.getJSONObject("value"));

                            list.add(aaa);
                        }

                        record.getWriteMethod().invoke(obj,list );



                        //属性是对象类型
                    }else if(record.getPropertyType().getName().equals(BaseGWRpcBean.class.getName())
                            ||
                                (record.getPropertyType().getSuperclass()!=null
                                &&
                                record.getPropertyType().getSuperclass().getClass().getName().equals(BaseGWRpcBean.class.getName())
                                )){


                        JSONObject object = (JSONObject)getKey(record.getName(),jsonObject);


                        Class classData =Class.forName(object.getString("classname"));

                        Object aaa = toBean(classData.newInstance().getClass(),map,object.getJSONObject("value"));
                        record.getWriteMethod().invoke(obj,aaa);

                    }else{//非对象

                        Object jsonName = getKey(record.getName(),jsonObject);

                        if(map.containsKey(jsonName)){
                            record.getWriteMethod().invoke(obj, map.get(jsonName));
                        }


                    }

                }catch(Exception e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            });

        return (T) obj;



    }



    /**
     * 将一个 JavaBean 对象转化为一个 Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     */
    public static Map toObjectMap(Object bean, JSONObject jsonObject) {

        Class<? extends Object> clazz = bean.getClass();
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        BeanInfo beanInfo = null;
        try {

            beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            Arrays.stream(propertyDescriptors).forEach(record -> {

                PropertyDescriptor descriptor = record;
                String propertyName = descriptor.getName();
                if (propertyName.equals("class")) {
                    return;//stream的return与正常for循环不一样,此处是进行下一次循环
                }
                Method readMethod = descriptor.getReadMethod();
                Object result = null;

                try{
                    result = readMethod.invoke(bean, new Object[0]);
                }catch(Exception e){
                    e.printStackTrace();
                }

                if (null != propertyName) {
                    propertyName = propertyName.toString();
                }

                if(null!= result && result.getClass().getSuperclass().equals(BaseGWRpcBean.class)){
                    JSONObject object = new JSONObject();
                    if(jsonObject.getJSONObject(propertyName).containsKey("value")){
                        object = jsonObject.getJSONObject(propertyName).getJSONObject("value");
                    }
                    String className = jsonObject.getJSONObject(propertyName).getString("classname");
                    Map map = toObjectMap(result,object);
                    map.put("class","".equals(className)?result.getClass().getName():className);
                    result = map;

                    if(jsonObject.getJSONObject(propertyName).containsKey("key")){
                        propertyName = jsonObject.getJSONObject(propertyName).getString("key");
                    }

                    returnMap.put(propertyName, result);
                    return;
                }

                result = null==result?null:result.toString();
                //进行字段映射，key为来源,value为目标
                if(jsonObject.containsKey(propertyName)){
                    String value = jsonObject.getString(propertyName);
                    propertyName = value;
                }

                returnMap.put(propertyName, result);

            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnMap;

    }


    /**
     * 将bean转换为map集合,不考虑key重复问题
     * @param bean
     * @return
     */
    public static Map toObjectMap(Object bean) {

        Class<? extends Object> clazz = bean.getClass();
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        BeanInfo beanInfo = null;
        try {

            beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            Arrays.stream(propertyDescriptors).forEach(record -> {

                PropertyDescriptor descriptor = record;
                String propertyName = descriptor.getName();
                if (propertyName.equals("class")) {
                    return;//stream的return与正常for循环不一样,此处是进行下一次循环
                }
                Method readMethod = descriptor.getReadMethod();
                Object result = null;

                try{
                    result = readMethod.invoke(bean, new Object[0]);
                }catch(Exception e){
                    e.printStackTrace();
                }

                if (null != propertyName) {
                    propertyName = propertyName.toString();
                }

                if(null!= result && result.getClass().getSuperclass().equals(BaseGWRpcBean.class)){

                    Map map = toObjectMap(result);

                    result = map;
                    returnMap.putAll(map);
                }

                result = null==result?null:result.toString();

                returnMap.put(propertyName, result);

            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnMap;

    }


    //转换服务端需要的对象,映射信息从map集合中获取
    public static Map toObjectMap(JSONObject jsonObject,Map map) {

        Iterator iterator = jsonObject.keys();

        Map returnMap = new HashMap();

        while(iterator.hasNext()){
            String key = (String) iterator.next();
            Object value = jsonObject.get(key);

            if (value instanceof JSONObject){

                Map cReturnMap = toObjectMap((JSONObject)value,map);
                if(key.equals("value")){
                    returnMap.putAll(cReturnMap);
                }else{
                    returnMap.put(key,cReturnMap);
                }

                continue;
            }

            if(map.containsKey(value)){
                value = map.get(value);
            }

            if("classname".equals(key)){
                key = "class";
            }

            returnMap.put(key,value);

        }


        return returnMap;

    }


    private static Object getJsonValue(Map map, String keys){

        Object objReturn  = map;

        String[] splitKeys = keys.split("\\.");

        for(String key : splitKeys){
            if(!((Map)objReturn).containsKey(key)){
                return null;
            }

            objReturn = ((Map)objReturn).get(key);

        }

        return objReturn;

    }

    private static Object getKey( String keys,JSONObject jsonObject){

        Object objReturn  = jsonObject;
        String[] splitKeys = keys.split("\\.");
        for(String key : splitKeys){

            if(!((JSONObject)objReturn).containsKey(key)){
                return keys;
            }

            objReturn = ((JSONObject)objReturn).get(key);

        }

        return objReturn;

    }




}