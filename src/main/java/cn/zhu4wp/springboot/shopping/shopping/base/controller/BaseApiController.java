package cn.zhu4wp.springboot.shopping.shopping.base.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class BaseApiController {
}
