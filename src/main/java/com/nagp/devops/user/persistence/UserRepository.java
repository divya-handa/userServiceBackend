package com.nagp.devops.user.persistence;

import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.entities.UserKey;

import java.util.List;

public interface UserRepository {

	public void create(User booking);

	public User get(UserKey bookingKey);
	public List<User> get();

	public void update(User booking);

	public void delete(UserKey userKey);

}
