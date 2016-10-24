package com.hrp.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("userServicesDao")
public class UserServicesDaoImpl extends AbstractDao implements UserServicesDao {

	@SuppressWarnings("unchecked")
	@Override
	public Long getRoleIdByUserId(Long userId) {
		
		Session session=getSession();
		
		List<Long > roleIds= session.createQuery("select us.role.id from UserServices us where us.user.id =:userId").setParameter("userId",userId).list();
		Long roleId=null;
		
		for(Long id: roleIds){
			System.out.println("id-------> "+id);
		}
		
		if(roleIds!=null&roleIds.size()>0){
			roleId= roleIds.get(0);
		}
		
		return roleId;
	}
	

}
