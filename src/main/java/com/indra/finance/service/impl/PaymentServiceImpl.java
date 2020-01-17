package com.indra.finance.service.impl;

import static com.indra.finance.error.Error.THERE_ARENT_PAYMENTS_FOR_THIS_DUTY;
import static com.indra.finance.error.Error.THE_CLIENT_DOESNT_EXIST;
import static com.indra.finance.error.Error.THE_DUTY_DOESNT_EXIST;
import static com.indra.finance.error.Error.THE_DUTY_HAS_ALREADY_BEEN_PAID;
import static com.indra.finance.error.Error.THE_PAYMENT_EXCEED_THE_VALUE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.indra.finance.dto.PaymentDTO;
import com.indra.finance.dto.PaymentResponseDTO;
import com.indra.finance.exception.ServiceException;
import com.indra.finance.helpers.BankName;
import com.indra.finance.helpers.Utils;
import com.indra.finance.model.Client;
import com.indra.finance.model.Duty;
import com.indra.finance.model.Payment;
import com.indra.finance.repository.ClientRepository;
import com.indra.finance.repository.DutyRepository;
import com.indra.finance.repository.PaymentRepository;
import com.indra.finance.service.PaymentService;

import lombok.extern.java.Log;

@Service
@Log
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	DutyRepository dutyRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	Utils utils;

	@Autowired
	private ModelMapper modelMapper;

	@Value("${payment.post}")
	private String saveMessage;

	@Override
	public PaymentResponseDTO makePayment(PaymentDTO paymentDTO) throws ServiceException {

		Optional<Duty> dutyCheck = dutyRepository.findById(paymentDTO.getDutyId());

		if (!dutyCheck.isPresent())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_DUTY_DOESNT_EXIST);

		Duty duty = dutyCheck.get();

		if (duty.getStatus().equals("PAGADO"))
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_DUTY_HAS_ALREADY_BEEN_PAID);

		Optional<Client> client = clientRepository.findById(paymentDTO.getClientIdentification());

		if (!client.isPresent())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_CLIENT_DOESNT_EXIST);

		if (paymentDTO.getPaymentValue() > duty.getCurrentPeriodValue()
				|| paymentDTO.getPaymentValue() > duty.getTotalValue())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_PAYMENT_EXCEED_THE_VALUE);

		Payment payment = new Payment();

		payment.setPaymentId(null);
		payment.setClientIdentification(paymentDTO.getClientIdentification());
		payment.setDutyId(paymentDTO.getDutyId());
		payment.setDutyName(duty.getDutyName());
		payment.setBankName(BankName.valueOf(utils.removeHyphen(paymentDTO.getBankName())).getCode());
		payment.setPaymenteDate(new Date());
		payment.setPaymentValue(paymentDTO.getPaymentValue());
		payment.setPaymentPeriod(paymentDTO.getPaymentPeriod());

		duty.setStatus(utils.dutyWasPaid(paymentDTO.getPaymentValue(), duty.getTotalValue()));
		duty.setTotalValue(duty.getTotalValue() - paymentDTO.getPaymentValue());
		duty.setCurrentPeriod(paymentDTO.getPaymentPeriod());

		paymentRepository.save(payment);
		dutyRepository.save(duty);

		log.info("Date :" + payment.getPaymenteDate() + " -- The client : " + client.get().getIdentification()
				+ " has made a payment to the duty with the id: " + duty.getDutyId() + " with value of : "
				+ paymentDTO.getPaymentValue() + " in the period number : " + paymentDTO.getPaymentPeriod());

		return new PaymentResponseDTO(saveMessage, payment.getPaymentId());
	}

	@Override
	public List<PaymentDTO> getPayments(String dutyId) throws ServiceException {

		Optional<List<Payment>> paymentList = paymentRepository.findByDutyId(dutyId);

		if (!paymentList.isPresent())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THERE_ARENT_PAYMENTS_FOR_THIS_DUTY);

		List<PaymentDTO> paymentListDTO = new ArrayList<>();
		for (Payment payment : paymentList.get()) {
			paymentListDTO.add(modelMapper.map(payment, PaymentDTO.class));
		}

		return paymentListDTO;
	}

}
