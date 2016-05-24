package bbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TopController {
	@RequestMapping(value = "/top/", method = RequestMethod.GET)
	public String showLoginScreen(Model model) {

		return "top";
	}
}
