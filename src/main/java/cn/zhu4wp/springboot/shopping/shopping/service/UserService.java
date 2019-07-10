package cn.zhu4wp.springboot.shopping.shopping.service;

import cn.zhu4wp.springboot.shopping.shopping.model.User;

public interface UserService {
    public User regist(User user);
    public User getUser(String username);
}
