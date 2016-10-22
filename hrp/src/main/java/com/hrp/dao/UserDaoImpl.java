package com.hrp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrp.model.Answer;
import com.hrp.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

	@SuppressWarnings("unchecked")
	public List<User> loginUser(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", username));
		List<User> useList = criteria.list();
		return useList;
	}

	public void registerUser(User user) {
		save(user);
	}

	@Override
	public Long isUser(String email) {

		Session session = getSession();
		Long id = (Long) session.createQuery("select id from User where email =:email").setParameter("email", email)
				.uniqueResult();

		return id;
	}

	@Override
	public Boolean checkAnswer(Answer answer) {

		Session session = getSession();
		Boolean flag = false;
		Long count = (Long) session.createQuery(
				"select count(*) from Answer a where a.answer =:answer and a.user.id =:userId and a.questions.id =:questionId")

				.setParameter("answer", answer.getAnswer()).setParameter("questionId", answer.getQuestions().getId())
				.setParameter("userId", answer.getUser().getId()).uniqueResult();

		if (count > 0)
			flag = true;

		return flag;
	}

	@Override
	public User getUserById(Long id) {
		Criteria criteria = getSession().createCriteria(User.class);
		return (User) criteria.add(Restrictions.eq("id", id)).uniqueResult();
	}

}
