package bbs.json;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bbs.form.ConvertForm;
import bbs.form.PostCommentForm;

@Service
public class JsonConverter {

	/**
	 * リストに格納されたデータをJSON文字列に変換するメソッド
	 * <p>
	 * @param list 変換したいデータが格納されたリスト。型は特定されない
	 * <p>
	 * @return JSON文字列化されたリスト内のデータ
	 * <p>
	 * @throws JsonProcessingException JSON文字列化する際に発生しうる例外。実行時にtry～catch文を用いて検出されたい。
	 */

	public <T> String parseJson(List<T> list) throws JsonProcessingException{
		if (list == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(list);

		return jsonStr;
	}


	/**
	 * 引数に渡されたデータをJSON文字列化するためのメソッド
	 *<p>
	 * @param value JSON文字列化するデータ
	 * @return JSON文字列化された引数のデータ
	 * @throws JsonProcessingException JSON文字列化の際に発生しうる例外
	 */

	public <T> String parseJson(T value) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(value);
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
		ConvertForm form = mapper.readValue(jsonId, ConvertForm.class);

		return (int) form.getValue();

	}

	public String parseJsonToString(String jsonStr) throws Exception{
		if(jsonStr == null){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		ConvertForm form = mapper.readValue(jsonStr, ConvertForm.class);

		return form.getValue().toString();
	}

}
