package bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
import bbs.form.PostMessageForm;
import bbs.mapper.MessageMapper;

@Service

public class MessageService {

	@Autowired
	private MessageMapper messageMapper;

	public List<String> getCategories() {

		List<String> categories = messageMapper.getCategories();
		categories.add(0, "カテゴリーを選択してください");
		return categories;
	}

	public List<MessageEntity> getAllMessage() {
		List<MessageEntity> messages = messageMapper.getAllMessage();
		for(MessageEntity m: messages){
			m.setElapsedTimeText(m.getElapsedTime());
		}
		return messages;
	}

	public List<CommentEntity> getComments() {
		List<CommentEntity> comments = messageMapper.getComments();
		for(CommentEntity c: comments){
			c.setElapsedTimeText(c.getElapsedTime());
		}
		return comments;
	}

	public Integer postMessage(PostMessageForm form) {
		return messageMapper.postMessage(form);
	}

	public Integer postComment(PostMessageForm form) {
		return messageMapper.postComment(form);
	}

	public Integer deleteMessage(int postId) {
		return messageMapper.deleteMessage(postId);

	}
}
