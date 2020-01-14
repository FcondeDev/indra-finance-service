package com.indra.finance.service;

import com.indra.finance.dto.ClientDTO;
import com.indra.finance.dto.ClientResponseDTO;

public interface ClientService {

	public ClientResponseDTO saveClient();

	public ClientDTO getClient();

	public ClientResponseDTO editClient();

	public ClientResponseDTO deleteClient();

}
