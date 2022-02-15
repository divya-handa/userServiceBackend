package com.nagp.devops.user.persistence.memory.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.entities.UserKey;
import com.nagp.devops.user.persistence.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryUserRepository implements UserRepository {

	private static final Map<UserKey, User> USERS = new HashMap<>();

	public MemoryUserRepository() {
		USERS.putAll(UserMocks.USER_MOCKS);
	}

	@Override
	public void create(User user) {
		USERS.put(user.getUserKey(), user);
	}

	@Override
	public void update(User user) {
		USERS.put(user.getUserKey(), user);
	}

	@Override
	public User get(UserKey userKey) {
		return USERS.get(userKey);
	}

	@Override
	public List<User> get() {
		return USERS.values().stream().collect(Collectors.toList());
	}

	@Override
	public void delete(UserKey userKey) {
		USERS.remove(userKey);
	}

}
