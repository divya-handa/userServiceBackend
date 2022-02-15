package com.nagp.devops.user.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.annotation.Resource;

import com.nagp.devops.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.entities.UserKey;
import com.nagp.devops.user.entities.UserState;
import com.nagp.devops.user.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	UserRepository userRepository;

	@Override
	public User create(User user) throws Exception {
		final User activatedUser = activateUser(user);
		userRepository.create(activatedUser);
		return get(activatedUser.getUserKey());
	}

	private User activateUser(User user) {
		return new User(UserKey.userKey(user.getUserKey().createUserId()), user.getUserName(), user.getAddress(),
				user.getAreaCode(), user.getUserType(), user.getContact(), user.isVaccinated(), UserState.ACTIVE);
	}

	@Override
	public User get(UserKey userKey) {
		return userRepository.get(userKey);
	}

    @Override
    public List<User> get() {
		return userRepository.get();
    }

    @Override
	public void update(User user) {
		validateUserExists(user.getUserKey());
		userRepository.update(user);

	}

	private void validateUserExists(UserKey userkey) {
		if (Objects.isNull(get(userkey))) {
			throw new NoSuchElementException("User does not exist with " + userkey);
		} else {
			logger.debug("User exists for {}", userkey);
		}
	}

	@Override
	public void delete(UserKey userkey) {
		validateUserExists(userkey);
		userRepository.delete(userkey);
	}

}
