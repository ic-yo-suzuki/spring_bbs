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

import bbs.entity.UserEntity;
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

		UserEntity editUser = (UserEntity) session.getAttribute("editUser");
		// session.removeAttribute("editUser");

		List<String> branches = userService.getBranches();
		branches.removeIf(a -> a.equals(editUser.getBranchName()));
		model.addAttribute("branches", branches);
		List<String> departments = userService.getDepartments();
		departments.removeIf(a -> a.equals(editUser.getDepartmentName()));
		model.addAttribute("departments", departments);
		model.addAttribute("editUser", editUser);
		model.addAttribute("message", session.getAttribute("message"));
		return "edituser";
	}

	@RequestMapping(value = "/manage/user/edit/", method = RequestMethod.POST)
	public String doEdit(@Valid @ModelAttribute EditUserForm form, BindingResult result, Model model,
			HttpServletRequest request) {
		System.out.println("名前：" + form.getName());

		UserEntity orgUserInfo = (UserEntity)request.getSession().getAttribute("editUser");
		System.out.println("Original : " + orgUserInfo.getLoginId());
		System.out.println("New      : " + form.getLoginId());
		System.out.println("Result.hasErrors() : " + result.hasErrors());
		System.out.println("Original.equals(new) : " + orgUserInfo.getLoginId().equals(form.getLoginId()));
		System.out.println("Exist? : " + userService.isExistLoginId(form.getLoginId()));

		boolean idCheck = false;

		if(orgUserInfo.getLoginId().equals(form.getLoginId())){
			idCheck = true;
		}else{
			idCheck = !userService.isExistLoginId(form.getLoginId());
		}

		if (!result.hasErrors() && idCheck) {
			form.setBranchId(userService.getBranchId(form.getBranchName()));
			form.setDepartmentId(userService.getDepartmentId(form.getDepartmentName()));
			if (request.getParameter("password").equals(request.getParameter("password_verify"))
					&& !StringUtils.isBlank(request.getParameter("password"))) {
				form.setPassword(new CipherUtil().encrypt(form.getPassword()));
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
			if (!(orgUserInfo.getLoginId().equals(form.getLoginId()))  && userService.isExistLoginId(form.getLoginId())) {
				message += "<br>入力されたログインIDは既に使用されています";
			}
			if(result.hasErrors()){
				System.out.println(result.toString());
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
