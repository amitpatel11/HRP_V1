package com.hrp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile {

	@Id
	@GeneratedValue
	Integer id;

	@ManyToOne
	@JoinColumn(name = "qualification_id")
	private Qualification qualification;

	@JoinColumn(name = "experience")
	private int experience;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "deleted_yn")
	private Boolean deletedYn;

	public UserProfile() {
		super();
	}

	public UserProfile(Integer id, Qualification qualification, int experience, String firstName, String middleName,
			String lastName, String mobile, Date createdDate, Date updatedDate, Boolean deletedYn) {
		super();
		this.id = id;
		this.qualification = qualification;
		this.experience = experience;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.deletedYn = deletedYn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
		return "UserProfile [id=" + id + ", qualification=" + qualification + ", experience=" + experience
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", mobile="
				+ mobile + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", deletedYn=" + deletedYn
				+ "]";
	}

}
