package com.indra.finance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.indra.finance.model.Duty;

public interface DutyRepository extends MongoRepository<Duty, Long> {
	
	Optional<List<Duty>> findByClientIdentification(Long clientIdentification);

}
