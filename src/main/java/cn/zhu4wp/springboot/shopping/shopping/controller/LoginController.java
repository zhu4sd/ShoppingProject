package cn.zhu4wp.springboot.shopping.shopping.controller;

import cn.zhu4wp.springboot.shopping.shopping.model.User;
import cn.zhu4wp.springboot.shopping.shopping.service.UserService;
import cn.zhu4wp.springboot.shopping.shopping.util.MD5Util;
import cn.zhu4wp.springboot.shopping.shopping.util.ValidateCode;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.Validate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class LoginController {
//    private static Logger log = (Logger) LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("title","登陆界面");
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult bindingResult, HttpSession session, ModelAndView mv){
        if(bindingResult.hasErrors()){
            return "login";
        }
//        log.info(("---------------"+user.getPassword()));
        //验证
        User temp = userService.getUser(user.getUsername());
        if(temp!=null){
            String inputPasswords = MD5Util.encrypt4Verify(user.getPassword());
            if (temp.getPassword().equals(inputPasswords)){
                //保存session
                session.setAttribute("user",temp);
                return "home";
            }else{
                return "login";
            }
        }else{
            return "login";
        }
    }
    @RequestMapping(value = "/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //设置相应的类型为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        HttpSession session = request.getSession();
        ValidateCode code = new ValidateCode(120,34,5,100);
        session.setAttribute("code",code.getCode());
        code.write(response.getOutputStream());
        return null;

    }

}
