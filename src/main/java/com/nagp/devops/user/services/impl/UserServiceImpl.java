package com.nagp.devops.user.services.impl;

import java.util.List;

import javax.annotation.Resource;

import com.nagp.devops.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userRepository;

	@Override
	public User create(User user) throws Exception {
		userRepository.save(user);
		return get(user.getUserId());
	}

	@Override
	public User get(Long userId) {
		return userRepository.getById(userId);
	}

    @Override
    public List<User> get() {
		return userRepository.findAll();
    }

    @Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}

}
