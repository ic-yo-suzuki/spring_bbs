package bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Test {
    @RequestMapping(value = "/hoge.html", method = RequestMethod.GET)
    public String start() {
        return "hoge";
    }
}