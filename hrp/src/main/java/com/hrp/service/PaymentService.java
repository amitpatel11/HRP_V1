package com.hrp.service;

import com.hrp.model.Payment;

public interface PaymentService {

	Payment getPayment(Long userId);

	Payment getPayment(Long userId, Long serviceId, Long roleId);

	double getTotalAmount(Long userId, Long roleId);

}
