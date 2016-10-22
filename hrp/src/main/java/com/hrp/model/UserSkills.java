package com.hrp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_skills")
public class UserSkills {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", updatable = false, insertable = false)
	private User user;

	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "skill_id", updatable = false, insertable = false)
	private Skills skills;

	@Column(name="skill_id")
	private Long skillId;
	
	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "deleted_yn")
	private Boolean deletedYn;

	public UserSkills() {
		super();
	}

	public UserSkills(UserSkills userSkills) {
		super();
		this.id = userSkills.id;
		this.user = userSkills.user;
		this.skills = userSkills.skills;
		this.createdDate = userSkills.createdDate;
		this.updatedDate = userSkills.updatedDate;
		this.deletedYn = userSkills.deletedYn;
	}

	
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getDeletedYn() {
		return deletedYn;
	}

	public void setDeletedYn(Boolean deletedYn) {
		this.deletedYn = deletedYn;
	}

	@Override
	public String toString() {
		return "UserSkills [id=" + id + ", user=" + user + ", skills=" + skills + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", deletedYn=" + deletedYn + "]";
	}

}
