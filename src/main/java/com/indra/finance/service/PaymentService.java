package com.indra.finance.service;

import java.util.List;

import com.indra.finance.dto.PaymentDTO;
import com.indra.finance.dto.PaymentResponseDTO;
import com.indra.finance.exception.ServiceException;

public interface PaymentService {

	public PaymentResponseDTO makePayment(PaymentDTO paymentDTO) throws ServiceException;

	public List<PaymentDTO> getPayments(String dutyId) throws ServiceException;

}
