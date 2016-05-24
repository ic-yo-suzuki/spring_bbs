package bbs.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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

	@RequestMapping(value = "/signup/", method = RequestMethod.GET)
	public String showSignUpScreen(Model model) {

		UserForm form = new UserForm();
		init(form, model);

		return "signup";
	}

	@RequestMapping(value = "/signup/", method = RequestMethod.POST)
	public String doSignUp(@Valid @ModelAttribute UserForm form, BindingResult result, Model model,
			HttpServletRequest request) {
		System.out.println(request.getParameter("loginId").length());
		System.out.println(request.getParameter("name").length());
		System.out.println(request.getParameter("password").length());
		System.out.println(request.getParameter("password_verify").length());

		if (!result.hasErrors() && request.getParameter("password").equals(request.getParameter("password_verify"))) {
			form.setBranchId(userService.getBranchId(form.getBranchName()));
			form.setDepartmentId(userService.getDepartmentId(form.getDepartmentName()));
			form.setStatus(true);

			new CipherUtil();
			form.setPassword(CipherUtil.encrypt(form.getPassword()));

			form.setLastLoginDate(Calendar.getInstance().getTime());

			if (userService.entryUser(form))
				model.addAttribute("message", "入力値を受け付けました");
			else
				model.addAttribute("message", "登録に失敗しました");

		} else {
			String message = new String("エラーです");
			if (!(request.getParameter("password").equals(request.getParameter("password_verify")))) {
				message = "エラーです<br>入力されたパスワードが一致していません";
			}
			model.addAttribute("message", message);

		}

		init(form, model);

		return "signup";
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
