package com.indra.finance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.indra.finance.model.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

	Optional<Client> findByIdentificationAndDocumentType(String identification, String documentType);
}
