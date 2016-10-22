package com.hrp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "question")
	private String question;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "deleted_yn")
	private Boolean deletedYn;

	public Questions() {
		super();
	}

	public Questions(Long id, String question, Date createdDate, Date updatedDate, Boolean deletedYn) {
		super();
		this.id = id;
		this.question = question;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.deletedYn = deletedYn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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
		return "Questions [id=" + id + ", question=" + question + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", deletedYn=" + deletedYn + "]";
	}

}
