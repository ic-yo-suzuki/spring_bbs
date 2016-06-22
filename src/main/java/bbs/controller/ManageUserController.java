package bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bbs.entity.UserEntity;
import bbs.form.LogicalDeleteFormViaAjax;
import bbs.form.PhysicalDeleteFormViaAjax;
import bbs.form.UserForm;
import bbs.json.JsonConverter;
import bbs.service.UserService;

@Controller

public class ManageUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JsonConverter jsonConverter;

	@RequestMapping(value = "/manage/user/", method = RequestMethod.GET)
	public ModelAndView showPostScreen() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("users", userService.getUsers());
		modelAndView.addObject("message", "ユーザ管理");
		modelAndView.setViewName("usermanager");
		return modelAndView;
	}

	@RequestMapping(value = "/getUserList/", method = RequestMethod.GET)

	public @ResponseBody String getUsersList() {
		String jsonUserList = "";
		try {
			jsonUserList = jsonConverter.parseJson(userService.getUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonUserList;
	}

	@RequestMapping(params = "editUser", value = "/manage/user/", method = RequestMethod.POST)
	public String editUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request) {

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
	public String logicalDeleteUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request) {
		int target = Integer.parseInt(request.getParameter("logicalDeleteUser"));
		int own = ((UserEntity) request.getSession().getAttribute("loginUser")).getId();
		System.out.println("操作者：" + own + " 論理削除対象：" + target);
		if (!userService.logicalDeleteUser(target, own)) {
			model.addAttribute("errorMessage", "ユーザの論理削除に失敗しました");
			System.out.println("論理削除失敗");
		} else {
			if (userService.getStatus(target)) {
				model.addAttribute("successMessage", "ユーザの復元に成功しました");
				System.out.println("復元");
			} else {
				model.addAttribute("successMessage", "ユーザの論理削除に成功しました");
				System.out.println("論理削除成功");
			}
		}
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("message", "ユーザ管理");
		return "usermanager";
	}

	@RequestMapping(params = "physicalDeleteUser", method = RequestMethod.POST)
	public String physicalDeleteUser(@ModelAttribute UserForm form, Model model, HttpServletRequest request) {
		int target = Integer.parseInt(request.getParameter("physicalDeleteUser"));
		int own = ((UserEntity) request.getSession().getAttribute("loginUser")).getId();

		if (!userService.physicalDeleteUser(target, own)) {
			model.addAttribute("errorMessage", "ユーザの物理削除に失敗しました");
		} else {

			model.addAttribute("successMessage", "ユーザの物理削除に成功しました");
		}
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("message", "ユーザ管理");
		return "usermanager";
	}

	@RequestMapping(value = "/manage/user/delete/logical/id/{id}/", method = RequestMethod.GET)
	@ResponseBody
	public String logicalDeleteViaAjax(@PathVariable int id, Model model){
		System.out.println("論理削除開始。ユーザID：" + id);
		LogicalDeleteFormViaAjax result = new LogicalDeleteFormViaAjax();
		String jsonStr = "";
		try{
			if(userService.logicalDeleteUser(id)){
				result.setResult("success");
			}else{
				result.setResult("failure");
			}
			result.setUserStatus(userService.getStatus(id));
			jsonStr = jsonConverter.parseJson(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("論理削除：" + jsonStr);
		return jsonStr;
	}

	@RequestMapping(value = "/manage/user/delete/physical/id/{id}/", method = RequestMethod.GET)
	@ResponseBody
	public String physicalDeleteViaAjax(@PathVariable int id, Model model){
		System.out.println("物理削除開始。ユーザID：" + id);
		PhysicalDeleteFormViaAjax result = new PhysicalDeleteFormViaAjax();
		String jsonStr = "";
		try{
			if(userService.physicalDeleteUser(id)){
				result.setResult("success");
			}else{
				result.setResult("failure");
			}
			jsonStr = jsonConverter.parseJson(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("物理削除：" + jsonStr);
		return jsonStr;
	}
}
