package bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bbs.entity.UserEntity;
import bbs.form.UserForm;

public interface UserMapper {

	UserEntity getUser(@Param("loginId") String loginId, @Param("password") String password);

	List<String> getBranches();

	List<String> getDepartments();

	int getBranchId(String branchName);

	int getDepartmentId(String departmentName);

	int entryUser(UserForm form);

}
