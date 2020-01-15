package com.indra.finance.service;

import com.indra.finance.dto.ClientDTO;
import com.indra.finance.dto.ClientResponseDTO;
import com.indra.finance.exception.ServiceException;

public interface ClientService {

	public ClientResponseDTO saveClient(ClientDTO clientDTO) throws ServiceException;

	public ClientDTO getClient(String clientIdentification) throws ServiceException;

	public ClientResponseDTO editClient(ClientDTO clientDTO) throws ServiceException;

	public ClientResponseDTO deleteClient(String clientIdentification) throws ServiceException;

}
