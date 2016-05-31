package bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bbs.entity.UserEntity;
import bbs.form.EditUserForm;
import bbs.form.UserForm;

public interface UserMapper {

	Integer getUserId(@Param("loginId") String loginId, @Param("password") String password);

	UserEntity getUser(@Param("id") int id);

	List<String> getBranches();

	List<String> getDepartments();

	int getBranchId(String branchName);

	int getDepartmentId(String departmentName);

	int entryUser(UserForm form);

	List<UserEntity> getUsers();

	String getBranchName(@Param("id") int branchId);

	String getDepartmentName(@Param("id") int departmentId);

	void login(@Param("id") int id);

	boolean logicalDeleteUser(@Param("id") int id, @Param("status") boolean status);

	boolean physicalDeleteUser(@Param("id") int id);

	boolean getStatus(@Param("id") int id);

	int editUser(EditUserForm form);

	int editUserWithPassword(EditUserForm form);


}
