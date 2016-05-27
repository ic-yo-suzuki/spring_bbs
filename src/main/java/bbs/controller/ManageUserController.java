package bbs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.entity.UserEntity;
import bbs.form.UserForm;
import bbs.service.UserService;

@Controller
public class ManageUserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/manage/user/", method = RequestMethod.GET)
	public String showPostScreen(Model model) {

		model.addAttribute("users", userService.getUsers());

		model.addAttribute("message", "ユーザ管理");
		return "usermanager";
	}

	@RequestMapping(params = "edit", value = "/manage/user/edit/id/{id}", method = RequestMethod.POST)
	public String editUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request, @PathVariable int id){
//		id = Integer.parseInt(request.getParameter("user.id"));
		UserEntity editUser = userService.getUser(id);
		model.addAttribute("editUser", editUser);
		model.addAttribute("message", "ユーザ情報の変更");
		return "edituser";
	}
}
