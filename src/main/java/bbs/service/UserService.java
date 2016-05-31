package bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbs.entity.UserEntity;
import bbs.form.EditUserForm;
import bbs.form.UserForm;
import bbs.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public UserEntity getUser(String loginId, String password) {
		Integer id = userMapper.getUserId(loginId, password);
		UserEntity entity = null;
		if(id != null){
			entity = getUser(id);
			userMapper.login(id);
		}
		return entity;
	}

	public UserEntity getUser(int id){
		UserEntity user = userMapper.getUser(id);
		user.setElapsedTimeText(user.getElapsedTime());
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
		for(UserEntity u: users){
			u.setDepartmentName(userMapper.getDepartmentName(u.getDepartmentId()));
			u.setBranchName(userMapper.getBranchName(u.getBranchId()));
			u.setElapsedTimeText(u.getElapsedTime());
		}

		return users;
	}

	public boolean logicalDeleteUser(int id){
		boolean status = !(getStatus(id));
		return userMapper.logicalDeleteUser(id, status);
	}

	public boolean physicalDeleteUser(int id){
		return userMapper.physicalDeleteUser(id);
	}

	public boolean getStatus(int id){
		return userMapper.getStatus(id);
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
}
