package cn.zhu4wp.springboot.shopping.shopping.redis;

import cn.zhu4wp.springboot.shopping.shopping.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author ZJM
 *
 */
@Repository
public class UserRedis extends BaseRedis<User> {
    private static final String REDIS_KEY="cn.zhu4wp.redis.UserRedis";
    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }

}
