package com.hrp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrp.model.UserServices;

@Repository("userServicesDao")
public class UserServicesDaoImpl extends AbstractDao implements UserServicesDao {

	@Override
	public List<UserServices> getAllActiveServicesByUserId(Long userId) {
		Criteria criteria = getSession().createCriteria(UserServices.class);
		criteria.add(Restrictions.eq("deletedYn", false));
		return criteria.add(Restrictions.eq("user.id", userId)).list();
	}

}
