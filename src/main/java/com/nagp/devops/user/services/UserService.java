package com.nagp.devops.user.services;

import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.entities.UserKey;

import java.util.List;

public interface UserService {

	public User create(User user) throws Exception;

	public User get(UserKey userKey);
	public List<User> get();

	public void update(User user);

	public void delete(UserKey userkey);

}
