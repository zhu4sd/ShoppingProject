package cn.zhu4wp.springboot.shopping.shopping.service.Impl;

import cn.zhu4wp.springboot.shopping.shopping.VO.UserVO;
import cn.zhu4wp.springboot.shopping.shopping.model.User;
import cn.zhu4wp.springboot.shopping.shopping.redis.UserRedis;
import cn.zhu4wp.springboot.shopping.shopping.repository.UserRepository;
import cn.zhu4wp.springboot.shopping.shopping.service.UserService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRedis userRedis;
    @Override
    public User regist(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserVO getUser(String username) {
        UserVO userVO = new UserVO();
        User user = userRedis.get("username");
        if (user == null){
            user = userRepository.findByUsername(username);
            if(user!=null){
                userRedis.put(user.getUsername(),user,-1);
            }else{
                return null;
            }
        }
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public void saveUserToRedisByToken(UserVO userVO, String token) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        userRedis.put(token,user,3600);
    }

    @Override
    public User getUserFromRedisByToken(String token) {
        return userRedis.get(token);
    }
}
