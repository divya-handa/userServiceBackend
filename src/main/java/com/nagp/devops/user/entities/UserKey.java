package com.nagp.devops.user.entities;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserKey {

	private final String userId;

	@JsonCreator
	private UserKey(@JsonProperty("userId") String userId) {
		this.userId = userId;
	}

	public static UserKey userKey(String userId) {
		return new UserKey(userId);
	}

	public String getUserId() {
		return userId;
	}

	public String createUserId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserKey other = (UserKey) obj;
		return Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "UserKey [userId=" + userId + "]";
	}

}
