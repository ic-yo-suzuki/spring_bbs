package bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/manage/ngword/get/", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getNgWordList(Model model){
		String jsonNgList = "";
		try{
			jsonNgList = jsonConverter.parseJson(messageService.getNgWord());
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonNgList;
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
		messageService.setNgWord(word);
		return ngwordJson;
	}

	@RequestMapping(value = "/manage/ngword/delete/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteNgWord(@PathVariable int id, Model model){

		if(messageService.deleteNgWord(id)){
			return "{\"result\":\"success\"}";
		}else{
			return "{\"result\":\"failure\"}";
		}

	}


}
