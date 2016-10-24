package com.hrp.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("userSkillsDao")
public class UserSkillsDaoImpl extends AbstractDao implements UserSkillsDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getSkillIdByUserId(Long userId) {

		Session session=getSession();

		return session.createQuery("select us.skillId from UserSkills us where us.user.id =:userId").setParameter("userId",userId).list();
	}
	
}
