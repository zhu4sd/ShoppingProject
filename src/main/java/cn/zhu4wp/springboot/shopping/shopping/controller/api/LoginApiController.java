package cn.zhu4wp.springboot.shopping.shopping.controller.api;

import cn.zhu4wp.springboot.shopping.shopping.VO.UserVO;
import cn.zhu4wp.springboot.shopping.shopping.base.controller.BaseApiController;
import cn.zhu4wp.springboot.shopping.shopping.base.result.Result;
import cn.zhu4wp.springboot.shopping.shopping.base.result.ResultCode;
import cn.zhu4wp.springboot.shopping.shopping.model.User;
import cn.zhu4wp.springboot.shopping.shopping.service.UserService;
import cn.zhu4wp.springboot.shopping.shopping.util.MD5Util;
import cn.zhu4wp.springboot.shopping.shopping.util.MyMD5Util;
import cn.zhu4wp.springboot.shopping.shopping.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author ZJM
 * 登陆 API
 */
@RestController
public class LoginApiController extends BaseApiController {
    @Autowired
    public UserService userService;

    @RequestMapping(value = "/login")
    public Result<Object> login(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult, HttpSession session,
                                String code, Model mode, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return Result.failure();
        } else {
            UserVO dbUser = userService.getUser(user.getUsername());
            if (dbUser == null) {
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            } else {
                if (dbUser.getPassword().equals(MD5Util.inputToDb(user.getPassword(), dbUser.getDbflag()))) {
                    String token = UUIDUtil.getUUID();
                    userService.saveUserToRedisByToken(dbUser, token);

                    Cookie cookie = new Cookie("token", token);
                    cookie.setMaxAge(3600);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return Result.success();
                }else{
                    return Result.failure(ResultCode.USER_LOGIN_ERROR);
                }
            }
        }
    }
}
