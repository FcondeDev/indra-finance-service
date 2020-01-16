package com.indra.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indra.finance.dto.PaymentDTO;
import com.indra.finance.dto.PaymentResponseDTO;
import com.indra.finance.exception.ServiceException;
import com.indra.finance.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PostMapping("payments")
	public ResponseEntity<PaymentResponseDTO> store(@RequestBody @Validated PaymentDTO paymentDTO)
			throws ServiceException {
		return new ResponseEntity<>(paymentService.makePayment(paymentDTO), HttpStatus.OK);

	}

	@GetMapping("payments/{dutyId}")
	public ResponseEntity<List<PaymentDTO>> index(@PathVariable String dutyId) throws ServiceException {
		return new ResponseEntity<>(paymentService.getPayments(dutyId), HttpStatus.OK);

	}
}
