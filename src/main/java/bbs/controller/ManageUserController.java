package bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

		int id = Integer.parseInt(request.getParameter("editUser"));
		System.out.println(id);
		UserEntity editUser = userService.getUser(id);
		System.out.println(editUser.getBranchName());
		model.addAttribute("editUser", editUser);
		HttpSession session = request.getSession();
		session.setAttribute("editUser", editUser);
		session.setAttribute("message", "ユーザ情報の変更");
		return "redirect:/manage/user/edit/";
	}


	@RequestMapping(params = "logicalDeleteUser", method = RequestMethod.POST)
	public String logicalDeleteUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request){
		int target = Integer.parseInt(request.getParameter("logicalDeleteUser"));
		int own = ((UserEntity)request.getSession().getAttribute("loginUser")).getId();
		System.out.println("操作者：" + own + " 論理削除対象：" + target);
		if(!userService.logicalDeleteUser(target, own)){
			model.addAttribute("errorMessage", "ユーザの論理削除に失敗しました");
			System.out.println("論理削除失敗");
		}else{
			if(userService.getStatus(target)){
				model.addAttribute("successMessage", "ユーザの復元に成功しました");
				System.out.println("復元");
			}else{
				model.addAttribute("successMessage", "ユーザの論理削除に成功しました");
				System.out.println("論理削除成功");
			}
		}
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("message", "ユーザ管理");
		return "usermanager";
	}

	@RequestMapping(params = "physicalDeleteUser", method = RequestMethod.POST)
	public String physicalDeleteUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request){
		int target = Integer.parseInt(request.getParameter("physicalDeleteUser"));
		int own = ((UserEntity)request.getSession().getAttribute("loginUser")).getId();

		if(!userService.physicalDeleteUser(target, own)){
			model.addAttribute("errorMessage", "ユーザの物理削除に失敗しました");
		}else{

			model.addAttribute("successMessage", "ユーザの物理削除に成功しました");
		}
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("message", "ユーザ管理");
		return "usermanager";
	}
}
