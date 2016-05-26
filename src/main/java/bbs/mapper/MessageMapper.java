package bbs.mapper;

import java.util.List;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
import bbs.form.PostCommentForm;
import bbs.form.PostMessageForm;

public interface MessageMapper {

	List<String> getCategories();

	List<MessageEntity> getAllMessage();

	List<CommentEntity> getComments();

	Integer postMessage(PostMessageForm form);

	Integer postComment(PostCommentForm form);

	int deleteMessage(int postId);

	int deleteCommentWithMessage(int postId);

	int getMessageCount();

	int deleteComment(int postId);




}
