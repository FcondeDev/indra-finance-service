package com.indra.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indra.finance.dto.ClientDTO;
import com.indra.finance.dto.ClientResponseDTO;
import com.indra.finance.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	
	@PostMapping("clients")
	public ResponseEntity<ClientResponseDTO> store(@RequestBody ClientDTO clientDTO){
		return null;
		
	}

}
