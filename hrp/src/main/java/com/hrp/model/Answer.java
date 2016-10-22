package com.hrp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", updatable = false, insertable = false)
	private Questions questions;

	@Column(name = "question_id")
	private Long questionId;

	@Column(name = "answer")
	private String answer;

	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "deleted_yn")
	private Boolean deletedYn;

	public Answer() {
		super();
	}

	public Answer(Long id, User user, Questions questions, Long questionId, String answer, Date createdDate,
			Date updatedDate, Boolean deletedYn) {
		super();
		this.id = id;
		this.user = user;
		this.questions = questions;
		this.questionId = questionId;
		this.answer = answer;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.deletedYn = deletedYn;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
		return "Answer [id=" + id + ", user=" + user + ", questions=" + questions + ", answer=" + answer
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", deletedYn=" + deletedYn + "]";
	}

}
