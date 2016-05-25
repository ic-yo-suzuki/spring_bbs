package bbs.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.form.NarrowingForm;
import bbs.form.PostCommentForm;
import bbs.form.PostMessageForm;
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

	@RequestMapping(value = "/top/", method = RequestMethod.POST)
	public String postComment(@Valid @ModelAttribute PostMessageForm form, BindingResult result, Model model){
		if(result.hasErrors() || messageService.postComment(form) == null){
			model.addAttribute("message", "エラー");
		}

		return "top";
	}

//	@RequestMapping(value = "/top/", method = RequestMethod.POST)
//	public String deleteMessage(@ModelAttribute MessageDeleteForm form, Model model){
//		if(messageService.deleteMessage(form.getPostId()) == null){
//			model.addAttribute("message", "エラー");
//		}
//		model.addAttribute("categories", messageService.getCategories());
//		model.addAttribute("narrowingForm", new NarrowingForm());
//		model.addAttribute("messages", messageService.getAllMessage());
//		model.addAttribute("comments", messageService.getComments());
//		model.addAttribute("postCommentForm", new PostCommentForm());
//		return "top";
//	}
}
