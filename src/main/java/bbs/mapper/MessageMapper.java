package bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	int deleteComment(int commentId);

	List<MessageEntity> getMessage(NarrowingForm form);

	List<MessageEntity> getMessageWithCategory(@Param("category") String category);

	List<MessageEntity> getMessageWithEndDate(@Param("end") String end);

	List<MessageEntity> getMessageWithStartDate(@Param("start") String start);

	List<MessageEntity> getMessageWithDate(@Param("start") String start, @Param("end") String end);

	List<MessageEntity> getMessageWithCategoryAndEndDate(@Param("category") String category, @Param("end") String end);

	List<MessageEntity> getMessageWithCategoryAndStartDate(@Param("category") String category, @Param("start") String start);

}
