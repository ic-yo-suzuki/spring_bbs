package bbs.mapper;

import java.util.List;

import bbs.entity.UserEntity;
import bbs.form.UserForm;

public interface UserMapper {

	UserEntity getUser(String loginId);

	List<String> getBranches();

	List<String> getDepartments();

	int getBranchId(String branchName);

	int getDepartmentId(String departmentName);

	int entryUser(UserForm form);
}
