package com.simptrad.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/home")
    @ResponseBody
    public String homeMethod() {
        System.out.println("---------------home begin");
        //打印request参数
        String content = "街溜子的穿搭艺术，在更深层面暗含了东北社会的一套生存哲学<br/>";
        content += "主要表现在三个方面：1、瞎溜达 2、递华子 3、 找好大哥。<br/>";
        content += "换言之，街溜子的生活，除了寻觅，就是等待。“没事，我溜达”，暗示他不需要每天坐班。";

        return "首页内容提示：<br/>"+content;
    }

    @GetMapping("/templ")
    //@ResponseBody
    public String templMethod() {
        System.out.println("---------------templ begin");
        return "home/templ";
    }
}
