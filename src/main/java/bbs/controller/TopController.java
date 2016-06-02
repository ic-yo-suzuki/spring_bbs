package bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bbs.entity.MessageEntity;
import bbs.form.DeleteCommentForm;
import bbs.form.DeleteMessageForm;
import bbs.form.NarrowingForm;
import bbs.form.PostCommentForm;
import bbs.service.MessageService;

@Controller
@RequestMapping(value = "/top/")
public class TopController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(method = RequestMethod.GET)
	public String showTopScreen(Model model) {
		System.out.println("bbs.controller.TopController#showTopScreen running.");
		init(model);

		return "top";
	}

	@RequestMapping(params = "postComment", method = RequestMethod.POST)
	public String postComment(@Valid @ModelAttribute PostCommentForm form, BindingResult result, Model model) {
		System.out.println(form.getPostId());
		if (result.hasErrors() || !messageService.isExistPost(form.getPostId())) {
			String message = "エラー：コメントの投稿に失敗しました。";
			if (!messageService.isExistPost(form.getPostId())) {
				message += "対象の投稿が存在しません。";
			}
			model.addAttribute("message", message);
			model.addAttribute("text", form.getText());
		} else {
			if (messageService.postComment(form) == null) {
				model.addAttribute("message", "エラー：コメントの投稿に失敗しました。");
				model.addAttribute("text", form.getText());
			}
			model.addAttribute("message", "コメントが投稿されました。");
		}
		init(model);
		return "top";
	}

	@RequestMapping(params = "deleteMessage", method = RequestMethod.POST)
	public String deleteMessage(@ModelAttribute DeleteMessageForm form, HttpServletRequest request, Model model) {

		boolean confirm = messageService.deleteMessage(Integer.parseInt(request.getParameter("deleteMessage")));
		if (!confirm) {
			model.addAttribute("message", "エラー：投稿の削除に失敗しました。");
		}else{
			model.addAttribute("message", "投稿の削除が完了しました。");
		}
		init(model);
		return "top";
	}

	@RequestMapping(params = "deleteComment", method = RequestMethod.POST)
	public String deleteComment(@ModelAttribute DeleteCommentForm form, HttpServletRequest request, Model model) {
		boolean confirm = messageService.deleteComment(Integer.parseInt(request.getParameter("deleteComment")));
		if (!confirm) {
			model.addAttribute("message", "エラー：コメントの削除に失敗しました。");

		}else{
			model.addAttribute("message", "コメントの削除が完了しました。");
		}
		init(model);
		return "top";
	}

	@RequestMapping(params = "narrow", method = RequestMethod.POST)
	public String narrowingMessage(@ModelAttribute NarrowingForm form, HttpServletRequest request, Model model) {

		List<MessageEntity> messages = messageService.getMessage(form);
		int count = messages.size();
		model.addAttribute("categories", messageService.getCategories());
		model.addAttribute("narrowingForm", new NarrowingForm());
		model.addAttribute("messages", messages);
		model.addAttribute("comments", messageService.getComments());
		model.addAttribute("postCommentForm", new PostCommentForm());
		model.addAttribute("postCount", count);
		String[] dates = new String[2];
		dates[0] = form.getStart();
		dates[1] = form.getEnd();
		model.addAttribute("dates", dates);
		String message = "検索条件<br> カテゴリ：" + form.getCategory() + "<br>" + "日付：" + form.getStart() + "～" + form.getEnd();
		model.addAttribute("narrowingMessage", message);
		return "top";
	}

	@RequestMapping(params = "reset", method = RequestMethod.POST)
	public String resetView(Model model) {
		return this.showTopScreen(model);
	}

	private void init(Model model) {
		model.addAttribute("categories", messageService.getCategories());
		model.addAttribute("narrowingForm", new NarrowingForm());
		model.addAttribute("messages", messageService.getAllMessage());
		model.addAttribute("comments", messageService.getComments());
		model.addAttribute("postCommentForm", new PostCommentForm());
		model.addAttribute("postCount", messageService.getMessageCount());
	}
}
