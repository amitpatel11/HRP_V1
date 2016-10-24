package com.hrp.dao;

import com.hrp.model.Payment;

public interface PaymentDao{

	Payment getPayment(Long userId);

	Payment getPayment(Long userId, Long serviceId, Long roleId);

	double getTotalAmount(Long userId, Long roleId);

}
