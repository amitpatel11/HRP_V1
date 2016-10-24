package com.hrp.dto;

import java.util.List;

import com.hrp.model.Answer;
import com.hrp.model.User;
import com.hrp.model.UserProfile;

public class UserRegistrationDTO {
	private User user;
	private UserProfile userProfile;
	private List<Answer> answers;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "UserRegistrationDTO [user=" + user + ", userProfile=" + userProfile + ", answers=" + answers + "]";
	}

}
