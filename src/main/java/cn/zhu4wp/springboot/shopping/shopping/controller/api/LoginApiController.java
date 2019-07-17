package cn.zhu4wp.springboot.shopping.shopping.controller.api;

import cn.zhu4wp.springboot.shopping.shopping.VO.UserVO;
import cn.zhu4wp.springboot.shopping.shopping.base.controller.BaseApiController;
import cn.zhu4wp.springboot.shopping.shopping.base.result.Result;
import cn.zhu4wp.springboot.shopping.shopping.base.result.ResultCode;
import cn.zhu4wp.springboot.shopping.shopping.model.User;
import cn.zhu4wp.springboot.shopping.shopping.service.UserService;
import cn.zhu4wp.springboot.shopping.shopping.util.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginApiController extends BaseApiController {
    @Autowired
    public UserService userService;
    @RequestMapping(value = "/login")
    public Result<Object> login(@ModelAttribute(value = "user")@Valid User user, BindingResult bindingResult, HttpSession session,
                                String code, Model mode, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return Result.failure();
        }else{
            UserVO dbUser = userService.getUser(user.getUsername());
            if(user1 == null){
                return Result.failure(ResultCode.USER_LOGIN_ERROR)
            }else{
                if (user1.getPassword().equals(MyMD5Util.encrypt4Verify(user1.getPassword()))){

                }
            }
        }
    }
}
