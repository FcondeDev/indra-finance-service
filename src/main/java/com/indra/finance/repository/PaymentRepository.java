package com.indra.finance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.indra.finance.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

	Optional<List<Payment>> findByDutyId(String dutyId);
}
