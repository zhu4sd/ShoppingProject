package cn.zhu4wp.springboot.shopping.shopping.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MyMD5Util {

    private static char[] hex = {'0','1','2','3','A','B','C','D','E','F','G','H','4','5','6','7'};

    /**
     * 随机生成长度为16的字符串
     * @return 产生的盐
     *
     */
    private static String creatSalt(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    private static String addSalt(String str,String salt){
        final String STR_WITH_SALT = str + salt;
        return STR_WITH_SALT;
    }

    /**
     * 使用MD5 进行加密
     * @param str 返回加密好的字符串
     */
    private static  String encryptByMD5(String str){
        final String MD5_STR = DigestUtils.md5Hex(str);
        return MD5_STR;
    }

    /**
     * 保存盐
     * @param salt 盐
     * @param md5WithSalt 加盐加密后的字符串
     * @return 带盐加密值
     */
    private static String saveSalt(String salt,String md5WithSalt){
        char[] str = new char[48];

        for (int i = 0; i <32 ; i++) {
            str[i] = md5WithSalt.charAt(i);
        }
        int j = 0;
        for (int i = 32; i < 48; i++) {
            str[i] = salt.charAt(j);
            j++;
        }
        return str.toString();
    }
    /**
     * 加盐加密
     * @param str
     * @return 加密值
     */
    public static String encrypt(String str){
        //生成盐
        String salt = creatSalt();
        //加盐
        str = addSalt(str,salt);
        //加密
        str = encryptByMD5(str);
        //保存盐
        str = saveSalt(salt,str);
        return str;
    }

    /**
     * 获取盐值
     * @param str
     * @return 返回盐值
     */
    private static String getSalt(String str){
        char[] salt = new char[16];
        int j = 0;
        for (int i = 32; i < 48; i++) {
            salt[j] = str.charAt(i);
            j++;
        }
        return salt.toString();
    }

    /**
     * 用于验证
     * @param str 数据库中的哈希值
     * @param salt 获取到的盐值
     * @return
     */
    private static String encrypt(String str,String salt){
        //加盐
        str = addSalt(str,salt);
        //加密
        str = encryptByMD5(str);
        return str;
    }

    /**
     * 验证
     * @param str 数据库中的哈希值
     * @return 返回生成的加盐加密字符串
     */
    public static String encrypt4Verify(String str){
        String salt = getSalt(str);
        return encrypt(str,salt);
    }
}
