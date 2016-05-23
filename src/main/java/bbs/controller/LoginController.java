package bbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.entity.User;
import bbs.form.LoginForm;
import bbs.service.UserService;


@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public String showLoginScreen(Model model){
		LoginForm form = new LoginForm();
		form.setLoginId("");

		form.setPassword("");
		model.addAttribute("loginForm", form);
		return "login";
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute LoginForm form, Model model){

		User user = userService.getUser(form.getLoginId());


		model.addAttribute("user", user);
		model.addAttribute("message", "入力値を受け付けました");


		return "login";

	}
}
