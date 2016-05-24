package bbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.entity.UserEntity;
import bbs.form.LoginForm;
import bbs.service.UserService;
import bbs.util.CipherUtil;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public String showLoginScreen(Model model) {
		LoginForm form = new LoginForm();
		form.setLoginId("");

		form.setPassword("");
		model.addAttribute("loginForm", form);
		return "login";
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute LoginForm form, Model model) {

		new CipherUtil();
		String encryptedPassword = CipherUtil.encrypt(form.getPassword());
		UserEntity user = userService.getUser(form.getLoginId(), encryptedPassword);
		if(user != null)
			model.addAttribute("message", "ログインに成功しました");
		else
			model.addAttribute("message", "ログインに失敗しました");

		return "login";

	}
}
