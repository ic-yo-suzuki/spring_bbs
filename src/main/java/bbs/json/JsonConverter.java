package bbs.json;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bbs.entity.CommentEntity;
import bbs.entity.MessageEntity;
import bbs.entity.UserEntity;

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

	// public List<UserEntity> parseJsonToUserEntityList(String userJsonStr)
	// throws Exception {
	// if (userJsonStr.equals(null)) {
	// return null;
	// }
	// ObjectMapper mapper = new ObjectMapper();
	//
	// List<UserEntity> userList = new ArrayList<UserEntity>();
	//
	//
	// return userList;
	// }
}
