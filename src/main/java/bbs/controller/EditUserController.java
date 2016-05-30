package bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.form.EditUserForm;
import bbs.service.UserService;

@Controller
public class EditUserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/manage/user/edit/", method = RequestMethod.GET)
	public String showEditScreen(@ModelAttribute EditUserForm form, Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("showEditUser");
		HttpSession session = request.getSession();
		session.removeAttribute("message");

		List<String> branches = userService.getBranches();
		model.addAttribute("branches", branches);
		List<String> departments = userService.getDepartments();
		model.addAttribute("departments", departments);
		return "edituser";
	}

	@RequestMapping(value = "/manage/user/edit/", method = RequestMethod.POST)
	public String doEdit(@Valid @ModelAttribute EditUserForm form, BindingResult result, Model model,
			HttpServletRequest request) {
		System.out.println("doEdit");

		System.out.println(form.getPassword());

//		if (!result.hasErrors() && request.getParameter("password").equals(request.getParameter("password_verify"))) {
//			form.setBranchId(userService.getBranchId(form.getBranchName()));
//			form.setDepartmentId(userService.getDepartmentId(form.getDepartmentName()));
//			form.setStatus(true);
//
//			new CipherUtil();
//			form.setPassword(CipherUtil.encrypt(form.getPassword()));
//
//			form.setLastLoginDate(Calendar.getInstance().getTime());
//
//			if (!userService.entryUser(form))
//
//				model.addAttribute("message", "登録に失敗しました");
//
//		} else {
//			String message = new String("エラーです");
//			if (!(request.getParameter("password").equals(request.getParameter("password_verify")))) {
//				message = "エラーです<br>入力されたパスワードが一致していません";
//			}
//			model.addAttribute("message", message);
			model.addAttribute("name", form.getName());
			model.addAttribute("loginId", form.getLoginId());
			model.addAttribute("editUser", form);

			model.addAttribute("branches", userService.getBranches());
			model.addAttribute("departments", userService.getDepartments());
//			return "signup";
//
//		}
//		return "redirect:/manage/user/";
		return "edituser";

	}



}
