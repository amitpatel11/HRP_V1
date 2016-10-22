package com.hrp.service;

import java.util.List;

import com.hrp.model.Questions;

public interface QuestionsService {
	public void addQuestion(Questions question);
	public void updateQuestion(Questions question);
	public List<Questions> getTotalNumberOfQuestionsNotDeleted();
	public Questions getQuestionById(Long id);
	public void deleteQuestionById(Long id);
	public List<Questions> getAllQuestions();	
}
