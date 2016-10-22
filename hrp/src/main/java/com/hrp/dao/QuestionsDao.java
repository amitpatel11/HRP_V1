package com.hrp.dao;

import java.util.List;

import com.hrp.model.Questions;

public interface QuestionsDao extends Dao{
	public List<Questions> getTotalNumberOfQuestionsNotDeleted();
	public Questions getQuestionById(Long id);
	public void deleteQuestionById(Long id);
	public List<Questions> getAllQuestions();	
}
