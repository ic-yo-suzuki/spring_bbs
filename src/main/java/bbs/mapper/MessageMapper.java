package bbs.mapper;

import java.util.List;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
import bbs.form.PostMessageForm;

public interface MessageMapper {

	List<String> getCategories();

	List<MessageEntity> getAllMessage();

	List<CommentEntity> getComments();

	Integer postMessage(PostMessageForm form);

	Integer postComment(PostMessageForm form);

	Integer deleteMessage(int postId);

	int getMessageCount();




}
