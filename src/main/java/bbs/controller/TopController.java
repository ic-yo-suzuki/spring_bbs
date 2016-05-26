package bbs.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.form.DeleteCommentForm;
import bbs.form.DeleteMessageForm;
import bbs.form.NarrowingForm;
import bbs.form.PostCommentForm;
import bbs.service.MessageService;

@Controller
public class TopController {
	@Autowired
	private MessageService messageService;
	@RequestMapping(value = "/top/", method = RequestMethod.GET)
	public String showLoginScreen(Model model) {
		model.addAttribute("categories", messageService.getCategories());
		model.addAttribute("narrowingForm", new NarrowingForm());
		model.addAttribute("messages", messageService.getAllMessage());
		model.addAttribute("comments", messageService.getComments());
		model.addAttribute("postCommentForm", new PostCommentForm());
		model.addAttribute("postCount", messageService.getMessageCount());
		return "top";
	}

	@RequestMapping(value = "/top/post/comment/", method = RequestMethod.POST)
	public String postComment(@Valid @ModelAttribute PostCommentForm form, BindingResult result, Model model){
		if(result.hasErrors() || messageService.postComment(form) == null){
			model.addAttribute("message", "エラー");
		}
		model.addAttribute("categories", messageService.getCategories());
		model.addAttribute("narrowingForm", new NarrowingForm());
		model.addAttribute("messages", messageService.getAllMessage());
		model.addAttribute("comments", messageService.getComments());
		model.addAttribute("postCommentForm", new PostCommentForm());
		model.addAttribute("postCount", messageService.getMessageCount());
		return "redirect:/top/";
	}

	@RequestMapping(value = "/top/delete/message", method = RequestMethod.POST)
	public String deleteMessage(@ModelAttribute DeleteMessageForm form, HttpServletRequest request, Model model){

		int confirm = messageService.deleteMessage(Integer.parseInt(request.getParameter("id")));
		if(confirm != 1){
			model.addAttribute("message", "エラー");
		}
		model.addAttribute("categories", messageService.getCategories());
		model.addAttribute("narrowingForm", new NarrowingForm());
		model.addAttribute("messages", messageService.getAllMessage());
		model.addAttribute("comments", messageService.getComments());
		model.addAttribute("postCommentForm", new PostCommentForm());
		model.addAttribute("postCount", messageService.getMessageCount());
		return "redirect:/top/";
	}

	@RequestMapping(value = "/top/delete/comment", method = RequestMethod.POST)
	public String deleteComment(@ModelAttribute DeleteCommentForm form, HttpServletRequest request, Model model){
		int confirm = messageService.deleteMessage(Integer.parseInt(request.getParameter("id")));
		if(confirm != 1){
			model.addAttribute("message", "エラー");
		}
		model.addAttribute("categories", messageService.getCategories());
		model.addAttribute("narrowingForm", new NarrowingForm());
		model.addAttribute("messages", messageService.getAllMessage());
		model.addAttribute("comments", messageService.getComments());
		model.addAttribute("postCommentForm", new PostCommentForm());
		model.addAttribute("postCount", messageService.getMessageCount());
		return "redirect:/top/";
	}
}
