package cn.zhu4wp.springboot.shopping.shopping.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson.JSON;

public  abstract class BaseRedis<T> {
    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;
    @Resource
    protected HashOperations<String,String,T>hashOperations;

    /**
     * 获取存入redis中的库
     * @return
     */
    protected abstract String getRedisKey();
    /**
     * 存入
     * @param key
     * @param domain 对象
     * @param expire 过期时间（单位：秒），-1:不设过期时间
     */
    public void put(String key,T domain,long expire){
        hashOperations.put(getRedisKey(),key,domain);
        if (expire != -1){
            redisTemplate.expire(getRedisKey(),expire,TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     * @param key 传入key的名称
     */
    public void remove(String key){
        hashOperations.delete(getRedisKey(),key);
    }

    /**
     * 查询
     * @param key
     * @return
     */
    public T get(String key){
        return hashOperations.get(getRedisKey(),key);
    }

    /**
     * 获取当前redis库下的所有对象
     * @return
     */
    public List<T> getAll(){
        return hashOperations.values(getRedisKey());
    }

    /**
     * 查询当前redis库下的所有key
     * @return
     */
    public Set<String> getKeys(){
        return hashOperations.keys(getRedisKey());
    }

    /**
     * 判断key是否存入redis中
     * @param key
     * @return
     */
    public boolean isKeyExists(String key){
        return hashOperations.hasKey(getRedisKey(),key);
    }

    /**
     * 查询当前key下缓存数量
     * @return
     */
    public long count(){
        return hashOperations.size(getRedisKey());
    }
    
    public void empty(){
        Set<String> set = hashOperations.keys(getRedisKey());
        set.stream().forEach(key->hashOperations.delete(getRedisKey(),key));
    }



}
