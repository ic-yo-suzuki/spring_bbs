package bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbs.json.JsonConverter;
import bbs.service.MessageService;

@Controller
public class ManageNgWordController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private JsonConverter jsonConverter;

	@RequestMapping(value = "/manage/ngword/", method = RequestMethod.GET)
	public String showManageScreen(Model model){
		model.addAttribute("ngWordList", messageService.getNgWord());
		return "ngwordmanager";
	}

	@RequestMapping(value = "/manage/ngword/add/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addNgWord(@RequestBody String ngwordJson, Model model){
		String word = "";
		try{
			word = jsonConverter.parseJsonToString(ngwordJson);
		}catch(Exception e){
			e.printStackTrace();
		}


		return word;
	}

}
