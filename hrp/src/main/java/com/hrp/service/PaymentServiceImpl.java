package com.hrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.PaymentDao;
import com.hrp.model.Payment;

@Service("paymentService")
@Transactional("transactionManager")
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentDao paymentDao;
	
	@Override
	public Payment getPayment(Long userId) {
		
		return paymentDao. getPayment(userId) ;
	}

	@Override
	public Payment getPayment(Long userId, Long serviceId, Long roleId) {
		return paymentDao.getPayment(userId, serviceId, roleId);
	}

	@Override
	public double getTotalAmount(Long userId, Long roleId) {
	
		return paymentDao.getTotalAmount(userId,roleId);
	}

}
