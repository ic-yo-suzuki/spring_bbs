package bbs.json;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
import bbs.entity.UserEntity;
import bbs.form.DeleteForm;
import bbs.form.PostCommentForm;

public class JsonConverter {

	public String parseJsonFromUserList(List<UserEntity> userList) throws JsonProcessingException {
		if (userList == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(userList);
		System.out.println(jsonStr);

		return jsonStr;
	}
	public String parseJsonFromMessageList(List<MessageEntity> messageList) throws JsonProcessingException {
		if (messageList == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(messageList);
		System.out.println(jsonStr);

		return jsonStr;
	}

	public String parseJsonFromCommentList(List<CommentEntity> commentList) throws JsonProcessingException{
		if(commentList == null){
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(commentList);
		System.out.println(jsonStr);

		return jsonStr;
	}

	public String parseJesonFromBoolean(boolean value) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(value);
		System.out.println(jsonStr);

		return jsonStr;
	}

	public PostCommentForm parseJsonToPostCommentForm(String jsonCommentStr) throws Exception{
		if(jsonCommentStr == null){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		PostCommentForm form = mapper.readValue(jsonCommentStr, PostCommentForm.class);

		return form;
	}

	public int parseIntFromJsonId(String jsonId) throws Exception{
		if(jsonId == null){
			return -1;
		}
		ObjectMapper mapper = new ObjectMapper();
		DeleteForm form = mapper.readValue(jsonId, DeleteForm.class);

		return form.getId();

	}

}
