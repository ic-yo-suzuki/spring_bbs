package bbs.mapper;

import bbs.entity.User;

public interface UserMapper {

	User getUser(String loginId);
}
