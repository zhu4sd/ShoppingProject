package cn.zhu4wp.springboot.shopping.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/")
    @ResponseBody
    String returnHello(){
        return "hello world";
    }
}
