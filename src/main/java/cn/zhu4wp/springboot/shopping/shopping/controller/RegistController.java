package cn.zhu4wp.springboot.shopping.shopping.controller;

import cn.zhu4wp.springboot.shopping.shopping.model.User;
import cn.zhu4wp.springboot.shopping.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/toRegist",method = RequestMethod.GET)
    public ModelAndView toRegist(){

        User user = new User();
        return new ModelAndView("regist").addObject(user);
    }
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public ModelAndView regist(@ModelAttribute(value = "user")@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("regist");
        }else{
            User newUser = userService.regist(user);
            if (newUser != null){
                return new ModelAndView("regist");
            }else {
                return new ModelAndView("regist");
            }
        }
    }

}
