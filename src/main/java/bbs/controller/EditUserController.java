package bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.form.EditUserForm;
import bbs.service.UserService;
import bbs.util.CipherUtil;

@Controller
public class EditUserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/manage/user/edit/", method = RequestMethod.GET)
	public String showEditScreen(@ModelAttribute EditUserForm form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("showEditUser");
		HttpSession session = request.getSession();

		List<String> branches = userService.getBranches();
		model.addAttribute("branches", branches);
		List<String> departments = userService.getDepartments();
		model.addAttribute("departments", departments);
		model.addAttribute("editUser", session.getAttribute("editUser"));
		model.addAttribute("message", session.getAttribute("message"));
		return "edituser";
	}

	@RequestMapping(value = "/manage/user/edit/", method = RequestMethod.POST)
	public String doEdit(@Valid @ModelAttribute EditUserForm form, BindingResult result, Model model,
			HttpServletRequest request) {
		System.out.println("名前：" + form.getName());

		if (!result.hasErrors()) {
			form.setBranchId(userService.getBranchId(form.getBranchName()));
			form.setDepartmentId(userService.getDepartmentId(form.getDepartmentName()));
			if (request.getParameter("password").equals(request.getParameter("password_verify"))
					&& !StringUtils.isBlank(request.getParameter("password"))) {
				new CipherUtil();
				form.setPassword(CipherUtil.encrypt(form.getPassword()));
				if (!userService.editUserWithPassword(form)) {
					model.addAttribute("message", "変更に失敗しました");
				}
			} else if (StringUtils.isBlank(request.getParameter("password"))
					&& StringUtils.isBlank(request.getParameter("password_verify"))) {
				if (!userService.editUser(form)) {
					model.addAttribute("message", "変更に失敗しました");
				}
			}

		} else {
			String message = new String("エラーです");
			if (!(request.getParameter("password").equals(request.getParameter("password_verify")))) {
				message = "エラーです<br>入力されたパスワードが一致していません";
			}
			model.addAttribute("message", message);
			model.addAttribute("name", form.getName());
			model.addAttribute("loginId", form.getLoginId());
			model.addAttribute("editUser", form);
			System.out.println(form.getId());
			model.addAttribute("branches", userService.getBranches());
			model.addAttribute("departments", userService.getDepartments());
			return "edituser";
		}
		HttpSession session = request.getSession();
		session.removeAttribute("message");
		session.removeAttribute("editUser");
		return "redirect:/manage/user/";
	}
}
