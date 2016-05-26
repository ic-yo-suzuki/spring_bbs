package bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
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

	public Integer postComment(PostCommentForm form) {
		return messageMapper.postComment(form);
	}

	public int deleteMessage(int postId) throws ArithmeticException {
		return messageMapper.deleteMessage(postId) / messageMapper.deleteCommentWithMessage(postId);
	}

	public int getMessageCount(){
		return messageMapper.getMessageCount();
	}

	public int deleteComment(int postId) {
		return messageMapper.deleteComment(postId);
	}
}
