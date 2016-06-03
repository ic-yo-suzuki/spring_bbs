package bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbs.entity.UserEntity;
import bbs.form.EditUserForm;
import bbs.form.UserForm;
import bbs.mapper.MessageMapper;
import bbs.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MessageMapper messageMapper;

	public UserEntity getUser(String loginId, String password) {
		Integer id = userMapper.getUserId(loginId, password);
		UserEntity entity = null;
		if (id != null) {
			entity = getUser(id);
			userMapper.login(id);
		}
		return entity;
	}

	public UserEntity getUser(int id) {
		System.out.println("bbs.service.UserService#getUser running.");
		System.out.println("bbs.mapper.UserMapper#getUser(" + id + ") will run.");
		UserEntity user = null;
		try {
			user = userMapper.getUser(id);
			System.out.println(user);
		} catch (Exception e) {
			System.out.println("Exception in bbs.service.UserService#getUser");
			e.printStackTrace();
		}

		user.setElapsedTimeText(user.getElapsedTime());
		user.setBranchName(userMapper.getBranchName(user.getBranchId()));
		user.setDepartmentName(userMapper.getDepartmentName(user.getDepartmentId()));
		System.out.println("getUser end");
		return user;
	}

	public List<String> getBranches() {
		return userMapper.getBranches();
	}

	public List<String> getDepartments() {
		return userMapper.getDepartments();
	}

	public int getBranchId(String branchName) {
		return userMapper.getBranchId(branchName);
	}

	public int getDepartmentId(String departmentName) {
		return userMapper.getDepartmentId(departmentName);
	}

	public boolean entryUser(UserForm form) {
		if (userMapper.entryUser(form) != 1) {
			return false;
		}
		return true;
	}

	public List<UserEntity> getUsers() {
		List<UserEntity> users = userMapper.getUsers();
		for (UserEntity u : users) {
			u.setDepartmentName(userMapper.getDepartmentName(u.getDepartmentId()));
			u.setBranchName(userMapper.getBranchName(u.getBranchId()));
			u.setElapsedTimeText(u.getElapsedTime());
		}

		return users;
	}

	public boolean logicalDeleteUser(int target, int own) {
		if (target == own) {
			return false;
		}
		boolean status = !(getStatus(target));
		return userMapper.logicalDeleteUser(target, status);
	}

	public boolean physicalDeleteUser(int target, int own) {
		if (target == own)
			return false;
		messageMapper.deleteMessageWithUserId(target);
		messageMapper.deleteCommentWithUserId(target);
		return userMapper.physicalDeleteUser(target);
	}

	public Boolean getStatus(int id) {
		Boolean result = null;
		if(userMapper != null){
			result = userMapper.getStatus(id);
		}
		if(result == null){
			return false;
		}else{
			return result;
		}
	}

	public boolean editUser(EditUserForm form) {
		if (userMapper.editUser(form) != 1) {
			return false;
		}
		return true;
	}

	public boolean editUserWithPassword(EditUserForm form) {
		if (userMapper.editUserWithPassword(form) != 1) {
			return false;
		}
		return true;
	}

	public Boolean isExistUser(int id) {
		Boolean result = null;
		if(userMapper != null){
			result = userMapper.isExistUser(id);
		}
		if (result == null) {
			return false;
		} else {
			return result;
		}
	}

	public boolean isExistLoginId(String loginId) {
		return userMapper.isExistLoginId(loginId);
	}
}
