package cn.zhu4wp.springboot.shopping.shopping.service.Impl;

import cn.zhu4wp.springboot.shopping.shopping.model.User;
import cn.zhu4wp.springboot.shopping.shopping.repository.UserRepository;
import cn.zhu4wp.springboot.shopping.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User regist(User user) {
        return userRepository.saveAndFlush(user);
    }
}
