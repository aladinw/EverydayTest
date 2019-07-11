/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: xing_xin[xing_xin@suixingpay.com]
 * @date: 2018/8/14 下午2:22
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.example.utils;

import java.util.UUID;

/**
 * UUIDUtils
 *
 * @author: xing_xin[xing_xin@suixingpay.com]
 * @date: 2018/8/14 下午2:22
 * @version: V1.0
 * @review: xing_xin[xing_xin@suixingpay.com]/2018/8/14 下午2:22
 */
public class UUIDUtils {
    /**
     * getUUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
