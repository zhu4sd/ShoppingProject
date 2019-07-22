package cn.zhu4wp.springboot.shopping.shopping.util;

import java.util.UUID;

/**
 * 生成token作为Redis key
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
