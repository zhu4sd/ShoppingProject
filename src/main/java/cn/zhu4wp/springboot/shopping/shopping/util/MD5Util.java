package cn.zhu4wp.springboot.shopping.shopping.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    private static String salt = "springboot";
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }
    public static String inputToBack(String str){
        return md5(str+salt);
    }
    public static String backToDb(String str,String dbSalt){
        return md5(str+dbSalt);
    }
    public static String inputToDb(String str,String dbSalt){
        return backToDb(inputToBack(str),dbSalt);
    }

}
