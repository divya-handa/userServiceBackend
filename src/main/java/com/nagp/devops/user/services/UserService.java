package com.nagp.devops.user.services;

import com.nagp.devops.user.entities.User;

import java.util.List;

public interface UserService {

	public User create(User user) throws Exception;

	public User get(Long userId);
	public List<User> get();

	public void update(User user);

	public void delete(Long userId);

}
