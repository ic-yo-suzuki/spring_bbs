package bbs.controller;

import java.util.Calendar;
import java.util.Date;

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

import bbs.form.UserForm;
import bbs.service.UserService;
import bbs.util.CipherUtil;

@Controller
public class SignUpController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/manage/user/signup/", method = RequestMethod.GET)
	public String showSignUpScreen(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("message");
		UserForm form = new UserForm();
		init(form, model);

		return "signup";
	}

	@RequestMapping(value = "/manage/user/signup/", method = RequestMethod.POST)
	public String doSignUp(@Valid @ModelAttribute UserForm form, BindingResult result, Model model,
			HttpServletRequest request) {

		if (!result.hasErrors() && request.getParameter("password").equals(request.getParameter("password_verify"))
				&& !(userService.isExistLoginId(form.getLoginId()))) {
			form.setBranchId(userService.getBranchId(form.getBranchName()));
			form.setDepartmentId(userService.getDepartmentId(form.getDepartmentName()));
			form.setStatus(true);

			form.setPassword(new CipherUtil().encrypt(form.getPassword()));
			System.out.println(form.getPassword());

			form.setLastLoginDate(Calendar.getInstance().getTime());

			if (!userService.entryUser(form))

				model.addAttribute("message", "登録に失敗しました");

		} else {
			String message = new String("エラーです");
			if (!(request.getParameter("password").equals(request.getParameter("password_verify")))) {
				message = "エラーです<br>入力されたパスワードが一致していません";
			}
			if (userService.isExistLoginId(form.getLoginId())) {
				message += "<br>入力されたログインIDは既に使用されています";
			}
			model.addAttribute("message", message);
			model.addAttribute("name", form.getName());
			model.addAttribute("loginId", form.getLoginId());
			init(form, model);

			return "signup";

		}
		return "redirect:/manage/user/";

	}

	private void init(UserForm form, Model model) {
		form.setId(0);
		form.setBranchId(0);
		form.setDepartmentId(0);
		form.setStatus(false);
		form.setElapsedTime(new Date().getTime());
		form.setElapsedTimeText(form.getElapsedTime() / 1000);
		form.setLastLoginDate(Calendar.getInstance().getTime());

		model.addAttribute("userForm", form);

		model.addAttribute("branches", userService.getBranches());
		model.addAttribute("departments", userService.getDepartments());
	}

}
