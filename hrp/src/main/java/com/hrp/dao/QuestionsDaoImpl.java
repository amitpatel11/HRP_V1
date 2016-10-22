package com.hrp.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrp.model.Questions;

@Repository("questionsDao")
public class QuestionsDaoImpl extends AbstractDao implements QuestionsDao {

	@Override
	public List<Questions> getTotalNumberOfQuestionsNotDeleted() {
		// TODO Auto-generated method stub
		Session session = getSession();
		Criteria criteria = session.createCriteria(Questions.class);
		@SuppressWarnings("unchecked")
		List<Questions> questionsList = (List<Questions>)criteria.add(Restrictions.eq("deletedYn", false)).list();
		return questionsList;
	}

	@Override
	public Questions getQuestionById(Long id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Criteria criteria = session.createCriteria(Questions.class);
		return (Questions)criteria.add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public void deleteQuestionById(Long id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query = session.createQuery("update questions set deletedYn=true where id=:id").setParameter("id", id);
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getAllQuestions() {
		return getSession().createCriteria(Questions.class).add(Restrictions.eq("deletedYn", false)).list();
	}

}
