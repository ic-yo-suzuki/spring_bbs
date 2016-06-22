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


	/**
	 * 同期通信用の論理削除メソッド
	 * <br>
	 * 操作者と操作対象ユーザのIDが同一かどうかを比較、一致した場合は処理を継続せずfalseを返す。
	 * <br>
	 * それ以外の場合は論理削除操作を実施、結果に応じた戻り値を返す。
	 *<p>
	 * @param target 論理削除対象となるユーザのID
	 * <br>
	 * @param own 操作しているユーザのID
	 * <p>
	 * @return 論理削除操作の成否(true = 成功、false = 失敗)
	 *
	 */

	public boolean logicalDeleteUser(int target, int own) {
		if (target == own) {
			return false;
		}
		boolean status = !(getStatus(target));
		return userMapper.logicalDeleteUser(target, status);
	}


	/**
	 * 非同期通信用の論理削除メソッド
	 * <br>
	 * 論理削除操作を実施、結果に応じた戻り値を返す。
	 *<p>
	 * @param target 論理削除対象となるユーザのID
	 * <p>
	 * @return 論理削除操作の成否(true = 成功、false = 失敗)
	 *
	 */

	public boolean logicalDeleteUser(int target){
		boolean status = !(getStatus(target));
		return userMapper.logicalDeleteUser(target, status);
	}


	/**
	 * 同期通信用のユーザ物理削除メソッド
	 * <br>
	 * DB上にあるユーザデータを削除し、結果に応じた戻り値を返す。
	 * <br>
	 * 操作者と対象ユーザが同一の場合、<b>何もせず</b>に失敗を返す
	 * <p>
	 * @param target 削除対象となるユーザのID
	 * <br>
	 * @param own 操作を行うユーザのID
	 * <p>
	 * @return 物理削除操作の成否(true = 成功、false = 失敗)
	 */

	public boolean physicalDeleteUser(int target, int own) {
		if (target == own)
			return false;
		messageMapper.deleteMessageWithUserId(target);
		messageMapper.deleteCommentWithUserId(target);
		return userMapper.physicalDeleteUser(target);
	}


	/**
	 * 非同期通信用のユーザ物理削除メソッド
	 * <br>
	 *DB上にあるユーザデータを削除し、結果に応じた戻り値を返す。
	 *<p>
	 * @param target 削除対象となるユーザのID
	 *<p>
	 * @return 物理削除操作の成否(true = 成功、false = 失敗)
	 */

	public boolean physicalDeleteUser(int target){
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
