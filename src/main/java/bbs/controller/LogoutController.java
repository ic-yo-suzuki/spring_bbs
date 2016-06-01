package bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogoutController {
	@RequestMapping(value = "/logout/", method = RequestMethod.GET)
	public String Logout(Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
		System.out.println("bbs.controller.LogoutController#Logout running.");
		HttpSession session = request.getSession();
		session.invalidate();
		attributes.addFlashAttribute("message", "ログアウトしました");
		return "redirect:/login/";
	}
}
