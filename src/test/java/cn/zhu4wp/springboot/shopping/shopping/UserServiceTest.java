package cn.zhu4wp.springboot.shopping.shopping;

import cn.zhu4wp.springboot.shopping.shopping.model.User;
import cn.zhu4wp.springboot.shopping.shopping.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void registTest(){
        User user = new User();
        user.setPassword("sdfsadfsadf");
        user.setUsername("dsfjasldkf");
        userService.regist(user);
    }
}
