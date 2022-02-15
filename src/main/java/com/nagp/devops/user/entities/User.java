package com.nagp.devops.user.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private final UserKey userKey;
	private final String userName;
	private final String address;
	private final String areaCode;
	private final UserType userType;
	private final Contact contact;
	private final boolean vaccinated;
	private final UserState userState;

	@JsonCreator
	public User(@JsonProperty("userKey") UserKey userKey, @JsonProperty("userName") String userName,
			@JsonProperty("address") String address, @JsonProperty("areaCode") String areaCode,
			@JsonProperty("userType") UserType userType, @JsonProperty("contact") Contact contact,
			@JsonProperty("vaccinated") boolean vaccinated, @JsonProperty("userState") UserState userState) {
		this.userKey = userKey;
		this.userName = userName;
		this.address = address;
		this.areaCode = areaCode;
		this.userType = Objects.isNull(userType) ? UserType.GUEST : userType;
		this.contact = contact;
		this.vaccinated = vaccinated;
		this.userState = Objects.isNull(userState) ? UserState.UNKNOWN : userState;
	}

	public UserKey getUserKey() {
		return userKey;
	}

	public String getUserName() {
		return userName;
	}

	public String getAddress() {
		return address;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public UserType getUserType() {
		return userType;
	}

	public Contact getContact() {
		return contact;
	}

	public boolean isVaccinated() {
		return vaccinated;
	}

	public UserState getUserState() {
		return userState;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, areaCode, contact, userKey, userName, userType, vaccinated, userState);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(areaCode, other.areaCode)
				&& Objects.equals(contact, other.contact) && Objects.equals(userKey, other.userKey)
				&& Objects.equals(userName, other.userName) && userType == other.userType
				&& vaccinated == other.vaccinated && userState == other.userState;
	}

	@Override
	public String toString() {
		return "User [userKey=" + userKey + ", userName=" + userName + ", address=" + address + ", areaCode=" + areaCode
				+ ", userType=" + userType + ", contact=" + contact + ", vaccinated=" + vaccinated + ", userState="
				+ userState + "]";
	}

}
