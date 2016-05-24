package bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbs.entity.UserEntity;
import bbs.form.UserForm;
import bbs.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public UserEntity getUser(String loginId) {

		System.out.println(loginId);
		UserEntity entity = userMapper.getUser(loginId);
		System.out.println(entity.getLoginId());

		return entity;
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
}
