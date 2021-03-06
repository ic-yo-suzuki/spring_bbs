package bbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
import bbs.entity.NgWord;
import bbs.form.NarrowingForm;
import bbs.form.PostCommentForm;
import bbs.form.PostMessageForm;
import bbs.mapper.MessageMapper;

@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;

	public List<String> getCategories() {

		List<String> categories = messageMapper.getCategories();
		categories.add(0, "");
		return categories;
	}

	public List<MessageEntity> getAllMessage() {
		List<MessageEntity> messages = messageMapper.getAllMessage();
		for (MessageEntity m : messages) {
			m.setElapsedTimeText(m.getElapsedTime());
		}
		return messages;
	}

	public List<CommentEntity> getComments() {
		List<CommentEntity> comments = messageMapper.getComments();
		for (CommentEntity c : comments) {
			c.setElapsedTimeText(c.getElapsedTime());
		}
		return comments;
	}

	public Integer postMessage(PostMessageForm form) {
		return messageMapper.postMessage(form);
	}

	public Integer postComment(PostCommentForm form) {
		return messageMapper.postComment(form);
	}

	public boolean deleteMessage(int postId) {

		int messageDel = messageMapper.deleteMessage(postId);
		int commentDel = messageMapper.deleteCommentWithMessage(postId);
		if (messageDel + commentDel <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public int getMessageCount() {
		return messageMapper.getMessageCount();
	}

	public boolean deleteComment(int commentId) {

		int confirm = messageMapper.deleteComment(commentId);
		if (confirm != 1) {
			return false;
		} else {
			return true;
		}

	}

	public List<MessageEntity> getMessage(NarrowingForm form) {
		List<MessageEntity> message = new ArrayList<MessageEntity>();
		int category, start, end;
		category = form.getCategory().length();
		start = form.getStart().length();
		end = form.getEnd().length();

		String[] time = { " 00:00:00", " 23:59:59" };
		if (start == 0 && end == 0) { // カテゴリー検索
			message = messageMapper.getMessageWithCategory(form.getCategory());

		} else if (category == 0 && start == 0) { // 終了日付検索
			message = messageMapper.getMessageWithEndDate(form.getEnd() + time[1]);

		} else if (category == 0 && end == 0) { // 開始日付検索
			message = messageMapper.getMessageWithStartDate(form.getStart() + time[0]);

		} else if (category == 0) { // 期間指定検索
			if (form.getStart().compareTo(form.getEnd()) > 0) {
				String tmp = form.getStart();
				form.setStart(form.getEnd());
				form.setEnd(tmp);
			}
			message = messageMapper.getMessageWithDate(form.getStart() + time[0], form.getEnd() + time[1]);

		} else if (start == 0) { // カテゴリーと終了日時検索
			message = messageMapper.getMessageWithCategoryAndEndDate(form.getCategory(), form.getEnd() + time[1]);

		} else if (end == 0) { // カテゴリーと開始日時検索
			message = messageMapper.getMessageWithCategoryAndStartDate(form.getCategory(), form.getStart() + time[0]);

		} else { // カテゴリー及び期間指定検索
			if (form.getStart().compareTo(form.getEnd()) > 0) {
				String tmp = form.getStart();
				form.setStart(form.getEnd());
				form.setEnd(tmp);
			}
			form.setStart(form.getStart() + time[0]);
			form.setEnd(form.getEnd() + time[1]);
			message = messageMapper.getMessage(form);
		}

		for (MessageEntity m : message) {
			m.setElapsedTimeText(m.getElapsedTime());
		}
		return message;
	}

	public boolean isExistPost(int id) {
		return messageMapper.isExistPost(id);
	}

	public boolean isExistComment(int id) {
		return messageMapper.isExistComment(id);
	}

	public List<NgWord> getNgWord() {
		return messageMapper.getNgWord();
	}

	public boolean setNgWord(String word) {
		int count = messageMapper.setNgWord(word);
		if (count != 1) {
			return false;
		}
		return true;

	}

	public boolean deleteNgWord(int id) {
		if (messageMapper.deleteNgWord(id) != 1) {
			return false;
		}
		return true;
	}
}
