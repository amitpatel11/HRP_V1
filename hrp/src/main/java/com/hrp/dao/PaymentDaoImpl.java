package com.hrp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrp.model.Payment;

@Repository("paymentDao")
public class PaymentDaoImpl  extends AbstractDao implements PaymentDao{

	@Override
	public Payment getPayment(Long userId) {
		
		return (Payment) get(Payment.class,userId);
	}

	@Override
	public Payment getPayment(Long userId, Long serviceId, Long roleId) {
		Map<String, Object> properties = new HashMap<String , Object>();
		properties.put("userId", userId);
		properties.put("serviceId", serviceId);
		properties.put("roleId", roleId);
		
		return (Payment) getUniqueEntityByMatchingProperties(Payment.class, properties);
	}

	@Override
	public double getTotalAmount(Long userId, Long roleId) {
		
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.setProjection(Projections.sum("amount"));	
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("roleId", roleId));
		
		@SuppressWarnings("rawtypes")
		List list = criteria.list();
		double amount= 0.0;
		double tempAmount =0.0;
		
		System.out.println(list != null );
		
		if(list != null && list.size() > 0){
		
			for(Object object : list){

				if(object != null){
					tempAmount =(double) object;
					amount=amount + tempAmount;
				}
			}
		}
		
		return amount	;
	}

}
