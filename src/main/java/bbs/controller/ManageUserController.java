package bbs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(params = "editUser",value = "/manage/user/",  method = RequestMethod.POST)
	public String editUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request){
		int id = form.getId();
		UserEntity editUser = userService.getUser(id);
		model.addAttribute("editUser", editUser);
		model.addAttribute("message", "ユーザ情報の変更");
		return "/manage/user/edit/";
	}

	@RequestMapping(params = "logicalDeleteUser", method = RequestMethod.POST)
	public String logicalDeleteUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("logicalDeleteUser"));
		if(!userService.logicalDeleteUser(id)){
			model.addAttribute("errorMessage", "ユーザの論理削除に失敗しました");
		}else{
			if(userService.getStatus(id)){
				model.addAttribute("successMessage", "ユーザの復元に成功しました");
			}else{
				model.addAttribute("successMessage", "ユーザの論理削除に成功しました");
			}
		}
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("message", "ユーザ管理");
		return "usermanager";
	}

	@RequestMapping(params = "physicalDeleteUser", method = RequestMethod.POST)
	public String physicalDeleteUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("physicalDeleteUser"));
		if(!userService.physicalDeleteUser(id)){
			model.addAttribute("errorMessage", "ユーザの物理削除に失敗しました");
		}else{

			model.addAttribute("successMessage", "ユーザの物理削除に成功しました");
		}
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("message", "ユーザ管理");
		return "usermanager";
	}
}
