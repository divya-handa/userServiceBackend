package com.nagp.devops.user.persistence.memory.impl;

import java.util.HashMap;
import java.util.Map;

import com.nagp.devops.user.entities.UserKey;
import com.nagp.devops.user.entities.Contact;
import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.entities.UserState;
import com.nagp.devops.user.entities.UserType;

final class UserMocks {

	static final  Map<UserKey, User> USER_MOCKS = new HashMap<>();
	private static final User USER_1 = new User(UserKey.userKey("1u"), "guest1",
			"15, A-block,Sector-4, Bangalore", "143001", UserType.GUEST, new Contact("9896110099", "abc.gmail.com"),
			true, UserState.ACTIVE);
	static {
		USER_MOCKS.put(USER_1.getUserKey(), USER_1);
	}

}
