package com.hrp.dto;

import java.util.List;

import com.hrp.model.Role;
import com.hrp.model.Services;
import com.hrp.model.Skills;
import com.hrp.model.User;

public class UserServicesRegistration {

	private User user;
	private Services services;
	private List<Skills> skills;
	private Role role;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Services getServices() {
		return services;
	}
	public void setServices(Services services) {
		this.services = services;
	}
	public List<Skills> getSkills() {
		return skills;
	}
	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
		
	
}
