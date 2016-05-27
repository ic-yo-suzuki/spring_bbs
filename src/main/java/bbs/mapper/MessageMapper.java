package bbs.mapper;

import java.util.List;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
import bbs.form.NarrowingForm;
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

	List<MessageEntity> getMessage(NarrowingForm form);

	List<MessageEntity> getMessageWithCategory(String category);

	List<MessageEntity> getMessageWithEndDate(String end);

	List<MessageEntity> getMessageWithStartDate(String start);

	List<MessageEntity> getMessageWithDate(String start, String end);

	List<MessageEntity> getMessageWithCategoryAndEndDate(String category, String end);

	List<MessageEntity> getMessageWithCategoryAndStartDate(String category, String start);

}
