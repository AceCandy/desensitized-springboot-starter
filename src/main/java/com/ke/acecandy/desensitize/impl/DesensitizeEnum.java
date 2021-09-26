package com.ke.acecandy.desensitize.impl;

/**
 * 脱敏注解枚举
 *
 * @author tangningzhu
 * @date 2021/9/26
 */
public enum DesensitizeEnum {
    // 中文名
    CHINESE_NAME,
    // 身份证号
    ID_CARD,
    // 座机号
    FIXED_PHONE,
    // 手机号
    MOBILE_PHONE,
    // 地址
    ADDRESS,
    // 电子邮件
    EMAIL,
    // 密码
    PASSWORD,
    // 中国大陆车牌，包含普通车辆、新能源车辆
    CAR_LICENSE,
    // 银行卡
    BANK_CARD,
    // 自定义
    CUSTOM,
}