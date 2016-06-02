package bbs.check;

import org.springframework.beans.factory.annotation.Autowired;

import bbs.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class UserChecker {

	@Autowired
	@Getter
	@Setter
	private static UserService userService;

	@SuppressWarnings("null")
	public Boolean isValidUser(int id) {

		System.out.println("bbs.check.UserChecker#isValidUser running.");
		System.out.println("userService.toString() is : " + userService.toString());
		Boolean exist, status;
		boolean result = false;
		try {
			exist = userService.isExistUser(id);
			status = userService.getStatus(id);
			if(exist != null && status == null){
				result = exist & status;
			}
		} catch (Exception e) {
			System.out.println("Exception in bbs.check.UserChecker#isValidUser.");
			e.printStackTrace();
		}
		return result;
	}
}
