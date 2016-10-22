package com.hrp.model;

import static org.mockito.Matchers.startsWith;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "description")
	private String description;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "deleted_yn")
	private Boolean deletedYn;
	
	public Skills(){
		super();
	}

	public Skills(Skills skills){
		super();
		this.id = skills.id;
		this.name = skills.name;
		this.description = skills.description;
		this.createdDate = skills.createdDate;
		this.updatedDate = skills.updatedDate;
		this.deletedYn = skills.deletedYn;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Skills [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", description=" + description
				+ ", updatedDate=" + updatedDate + ", deletedYn=" + deletedYn + "]";
	}

}
