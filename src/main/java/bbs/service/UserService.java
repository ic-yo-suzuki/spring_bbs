package bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbs.entity.User;
import bbs.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

    public User getUser(String loginId) {

        System.out.println(loginId);
        User entity = userMapper.getUser(loginId);
        System.out.println(entity.getLoginId());


        return entity;
    }
}
