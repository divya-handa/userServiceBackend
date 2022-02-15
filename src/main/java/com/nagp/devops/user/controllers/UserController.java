package com.nagp.devops.user.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.annotation.Resource;

import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.entities.UserKey;
import com.nagp.devops.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class UserController {

	@Resource
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<?> create(@RequestBody final User user) {
		try {
			final User createdUser = userService.create(user);
			logger.debug("Successfully created user with {}", createdUser.getUserKey());
			return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/userId/{user_id}")
	public ResponseEntity<?> get(@PathVariable(name = "user_id") String userId) {
		logger.debug("Received request to fetch user details for userId: {}" + userId);
		final UserKey requestedUserKey = UserKey.userKey(userId);
		try {
			final User retrievedUser = userService.get(requestedUserKey);
			if (Objects.nonNull(retrievedUser)) {
				logger.info("Retrieved User : {}", retrievedUser);
				return new ResponseEntity<User>(retrievedUser, HttpStatus.OK);
			}
			logger.info("Failed to retrieve user for :{}", requestedUserKey);
			return new ResponseEntity<>("Failed to retrieve user for :" + requestedUserKey, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping
	public ResponseEntity<?> getAll() {
		logger.debug("Received request to fetch user details");
		try {
			final List<User> retrievedUser = userService.get();
			logger.info("Retrieved User : {}", retrievedUser);
			return new ResponseEntity<List<User>>(retrievedUser, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			userService.update(user);
			logger.debug("Successfully updated user with {}", user);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (NoSuchElementException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/userId/{user_id}")
	public ResponseEntity<?> delete(@PathVariable(name = "user_id") String userId) {
		try {
			logger.debug("Received request to delete user for userId: {}" + userId);
			final UserKey requestedUserKey = UserKey.userKey(userId);
			userService.delete(requestedUserKey);
			logger.debug("Successfully updated user with {}", requestedUserKey);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (NoSuchElementException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
