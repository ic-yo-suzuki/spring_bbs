package bbs.check;

import org.springframework.beans.factory.annotation.Autowired;

import bbs.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class UserChecker {

	@Autowired
	@Getter
	@Setter
	private UserService userService;

	public boolean isValidUser(int id){

		System.out.println("bbs.check.UserChecker#isValidUser running.");
		System.out.println("userService.toString() is : " + userService.toString());
		boolean result = false;
		try{
			result = userService.isExistUser(id) && userService.getStatus(id);
		}catch(Exception e){
			System.out.println("Exception in bbs.check.UserChecker#isValidUser.");
			e.printStackTrace();
		}
		return result;
	}
}
