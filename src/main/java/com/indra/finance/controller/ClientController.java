package com.indra.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indra.finance.dto.ClientDTO;
import com.indra.finance.dto.ClientResponseDTO;
import com.indra.finance.exception.ServiceException;
import com.indra.finance.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	ClientService clientService;

	@PostMapping("clients")
	public ResponseEntity<ClientResponseDTO> store(@RequestBody @Validated ClientDTO clientDTO) throws ServiceException {
		return new ResponseEntity<>(clientService.saveClient(clientDTO), HttpStatus.OK);

	}

	@GetMapping("clients/{clientIdentification}")
	public ResponseEntity<ClientDTO> show(@PathVariable String clientIdentification) throws ServiceException {
		return new ResponseEntity<>(clientService.getClient(clientIdentification), HttpStatus.OK);

	}

	@PutMapping("clients")
	public ResponseEntity<ClientResponseDTO> update(@RequestBody @Validated ClientDTO clientDTO) throws ServiceException {
		return new ResponseEntity<>(clientService.editClient(clientDTO), HttpStatus.OK);

	}

	@DeleteMapping("clients/{clientIdentification}")
	public ResponseEntity<ClientResponseDTO> destroy(@PathVariable String clientIdentification)
			throws ServiceException {
		return new ResponseEntity<>(clientService.deleteClient(clientIdentification), HttpStatus.OK);

	}

}
