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

import bbs.form.PostMessageForm;
import bbs.service.MessageService;

@Controller
public class PostMessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/post/message/", method = RequestMethod.GET)
	public String showPostScreen(Model model) {
		model.addAttribute("categories", messageService.getCategories());
		model.addAttribute("postMessageForm", new PostMessageForm());
		model.addAttribute("message", "新規投稿");
		return "newpost";
	}

	@RequestMapping(value = "/post/message/", method = RequestMethod.POST)
	public String postMessage(@Valid @ModelAttribute PostMessageForm form, BindingResult result, Model model, HttpServletRequest request){
		if(result.hasErrors() || form.getCategory().length() > 10  || messageService.postMessage(form) != 1){
			model.addAttribute("message", "エラー");
			model.addAttribute("categories", messageService.getCategories());
			model.addAttribute("selectedCategory", form.getCategory());
			model.addAttribute("title", form.getTitle());
			model.addAttribute("text", form.getText());
			return "newpost";
		}
		return "redirect:/top/";
	}
}
