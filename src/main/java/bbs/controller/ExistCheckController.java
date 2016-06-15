package bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import bbs.json.JsonConverter;
import bbs.service.UserService;

@Controller
public class ExistCheckController {
	@Autowired
	private UserService userService;


	@RequestMapping(value = "/check/exist/loginid/{inputValue}", method = RequestMethod.GET)
	@ResponseBody
	public String getResultForExistCheck(@PathVariable("inputValue") String inputValue){
		try {
			return new JsonConverter().parseJesonFromBoolean(userService.isExistLoginId(inputValue));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
