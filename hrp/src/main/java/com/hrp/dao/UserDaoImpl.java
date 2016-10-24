package com.hrp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	public void registerUser(List<Answer> answers) {

		for (Answer answer : answers) {
			answer.setDeletedYn(false);
			save(answer);
		}
		
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
	public Long getProfileIdByUserId(Long userId) {
		Session session=getSession();
		Long profileId=(Long)session.createQuery("select userProfile.id from User where id =:userId")
		 .setParameter("userId",userId).uniqueResult();
		return profileId;
	}

	@Override
	public Long saveUser(User user) {

		return (Long) getSession().save(user);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> selectProviders( Long serviceId,Long roleId,Integer experience) {

		String query="select u.id from User u  where  u.userProfile.experience >=:experience and  u.id in (select us.user.id from UserServices us where us.role.id !=:roleId and us.services.id =:serviceId )";
		
		Session session=getSession();
		List<Long> providersIds=null;
		providersIds=session.createQuery(query).setParameter("experience", experience).setParameter("roleId",roleId).setParameter("serviceId", serviceId).list();
		return providersIds ;
	}

	@Override
	public List<User> selectProvidersBasedOnExperience(Long serviceId, Long roleId, ArrayList<Long> idList) {
		
	//	String sql ="SELECT id FROM `user_profile` WHERE experience=(SELECT MAX(experience) FROM `user_profile`) AND id  NOT IN(23,24,25)";
		
		StringBuffer hql = new StringBuffer(" from UserProfile up  where experience=(select max(experience) from UserProfile up ) id not in (");
		boolean flag = false;

		for (Long id : idList) {
			if (flag == false) {
				hql.append(id);
			} else {
				if (idList.size() > 0) {
					hql.append(",");
					hql.append(id);
				}
			}
			flag = true;
		}

		hql.append(")");
		
		System.out.println(" HQL -----> "+hql);
		
		Query query = getSession().createQuery(hql.toString());

		 for(Iterator iterator=query.iterate(); iterator.hasNext();)
		  {
			 Long row = (Long) iterator.next();
			 System.out.print("MAX experience " + row);
		  }
		 
		// Criteria criteria = getSession().createCriteria(UserProfile.class).add(Restrictions.eq("experience", value));
		 
	//	String hql= "SELECT MAX(experience) FROM UserProfile up where id not in (:userId1, :userId2, :user)"
		
	/*	Criteria criteria = getSession().createCriteria(UserProfile.class);
        
		ProjectionList projList = Projections.projectionList();
        projList.add(Projections.max("experience"));
        projList.add(Projections.);
        projList.add(Projections.avg("price"));*/
		
		return null;
	}

	

}
