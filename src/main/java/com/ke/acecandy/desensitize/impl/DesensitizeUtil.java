package com.ke.acecandy.desensitize.impl;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 脱敏工具类
 *
 * @author tangningzhu
 * @date 2021/9/26
 */
public class DesensitizeUtil {

    private static final char FILL_ASTERISK = '*';
    private static final String INDEX_AREA = "区";
    private static final String INDEX_CITY = "市";

    /**
     * 脱敏
     *
     * @param str             需要脱敏的字符串
     * @param desensitizeEnum 脱敏枚举
     *
     * @return {@link String}
     */
    public static String desensitized(CharSequence str, DesensitizeEnum desensitizeEnum) {
        if (StrUtil.isBlank(str)) {
            return StrUtil.EMPTY;
        }
        String newStr = String.valueOf(str);
        switch (desensitizeEnum) {
            case CHINESE_NAME:
                newStr = chineseName(newStr);
                break;
            case ID_CARD:
                newStr = idCardNum(newStr);
                break;
            case FIXED_PHONE:
                newStr = fixedPhone(newStr);
                break;
            case MOBILE_PHONE:
                newStr = mobilePhone(newStr);
                break;
            case ADDRESS:
                newStr = address(newStr);
                break;
            case EMAIL:
                newStr = email(newStr);
                break;
            case PASSWORD:
                newStr = password(newStr);
                break;
            case CAR_LICENSE:
                newStr = carLicense(newStr);
                break;
            case BANK_CARD:
                newStr = bankCard(newStr);
                break;
            default:
        }
        return newStr;
    }

    /**
     * 中文姓名脱敏
     * <p>
     * 只显示第一个汉字，其他隐藏为2个星号 比如：李**
     *
     * @param fullName 姓名
     *
     * @return 脱敏后的姓名
     */
    public static String chineseName(String fullName) {
        if (StrUtil.isBlank(fullName)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(fullName, 1, fullName.length());
    }

    /**
     * 身份证号脱敏
     * <p>
     * 前6位后4位 比如：500383********5975
     *
     * @param idCardNum 身份证
     *
     * @return 脱敏后的身份证
     */
    public static String idCardNum(String idCardNum) {
        if (StrUtil.isBlank(idCardNum)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(idCardNum, 6, idCardNum.length() - 4);
    }

    /**
     * 固定电话脱敏
     * <p>
     * 前两位后两位 比如：44****33
     *
     * @param num 固定电话
     *
     * @return 脱敏后的固定电话；
     */
    public static String fixedPhone(String num) {
        if (StrUtil.isBlank(num)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(num, 2, num.length() - 2);
    }

    /**
     * 手机脱敏
     * <p>
     * 前三位后4其他隐藏 比如：135****2210
     *
     * @param num 移动电话；
     *
     * @return 脱敏后的移动电话；
     */
    public static String mobilePhone(String num) {
        if (StrUtil.isBlank(num)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(num, 3, num.length() - 4);
    }

    /**
     * 地址脱敏
     * <p>
     * 如果有区则显示到区 比如：北京市海淀区****
     * 否则显示到市 比如：北京市****
     * 没有区或者市剪切前6位 比如：北京海淀田园****
     * 少于6位只取一半 比如：北京**
     *
     * @param address 家庭住址
     *
     * @return 脱敏后的家庭地址
     */
    public static String address(String address) {
        if (StrUtil.isBlank(address)) {
            return StrUtil.EMPTY;
        }
        int length = address.length();
        if (StrUtil.contains(address, INDEX_AREA)) {
            int start = StrUtil.indexOfIgnoreCase(address, INDEX_AREA);
            return StrUtil.hide(address, start + 1, length);
        } else if (StrUtil.contains(address, INDEX_CITY)) {
            int start = StrUtil.indexOfIgnoreCase(address, INDEX_CITY);
            return StrUtil.hide(address, start + 1, length);
        } else {
            if (length > 6) {
                return StrUtil.hide(address, 6, length);
            } else {
                return StrUtil.hide(address, length / 2, length);
            }
        }
    }

    /**
     * 电子邮箱脱敏
     * <p>
     * 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示 比如：d**@126.com
     *
     * @param email 邮箱
     *
     * @return 脱敏后的邮箱
     */
    public static String email(String email) {
        if (StrUtil.isBlank(email)) {
            return StrUtil.EMPTY;
        }
        int index = StrUtil.indexOf(email, CharUtil.AT);
        if (index <= 1) {
            return email;
        }
        return StrUtil.hide(email, 1, index);
    }

    /**
     * 密码脱敏
     * <p>
     * 密码的全部字符换成8-16位*，比如：********
     *
     * @param password 密码
     *
     * @return 脱敏后的密码
     */
    public static String password(String password) {
        if (StrUtil.isBlank(password)) {
            return StrUtil.EMPTY;
        }
        int length = RandomUtil.randomInt(8, 17);
        return StrUtil.fillAfter(StrUtil.EMPTY, FILL_ASTERISK, length);
    }

    /**
     * 中国车牌脱敏
     * <p>
     * 前三后一 比如：苏D4***0（普通扯车牌）、陕A1****D（新能源车牌）
     *
     * @param carLicense 完整的车牌号
     *
     * @return 脱敏后的车牌
     */
    public static String carLicense(String carLicense) {
        if (StrUtil.isBlank(carLicense)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(carLicense, 3, carLicense.length() - 1);
    }

    /**
     * 银行卡号脱敏
     * <p>
     * 去除中间空格后前四后四 比如：1101************3256
     *
     * @param bankCardNo 银行卡号
     *
     * @return 脱敏之后的银行卡号
     */
    public static String bankCard(String bankCardNo) {
        if (StrUtil.isBlank(bankCardNo)) {
            return StrUtil.EMPTY;
        }
        bankCardNo = StrUtil.replace(bankCardNo, StrUtil.SPACE, StrUtil.EMPTY);
        return StrUtil.hide(bankCardNo, 4, bankCardNo.length() - 4);
    }

    /**
     * 自定义脱敏
     * <p>
     * 对start到end的字符进行脱敏 比如：11013256,2,2 => 11****56
     *
     * @param str 需要脱敏的字符串
     *
     * @return 脱敏之后的字符串
     */
    public static String custom(String str, int start, int end) {
        if (StrUtil.isBlank(str) || start == end) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(str, start, str.length() - end);
    }
}