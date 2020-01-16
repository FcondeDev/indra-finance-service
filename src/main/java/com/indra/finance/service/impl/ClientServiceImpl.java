package com.indra.finance.service.impl;

import static com.indra.finance.error.Error.THE_CLIENT_ALREADY_EXIST;
import static com.indra.finance.error.Error.THE_CLIENT_DOESNT_EXIST;
import static com.indra.finance.error.Error.THE_CLIENT_IS_DISABLED;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.indra.finance.dto.ClientDTO;
import com.indra.finance.dto.ClientResponseDTO;
import com.indra.finance.exception.ServiceException;
import com.indra.finance.helpers.DocumentType;
import com.indra.finance.helpers.Utils;
import com.indra.finance.model.Client;
import com.indra.finance.repository.ClientRepository;
import com.indra.finance.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	Utils utils;

	@Override
	public ClientResponseDTO saveClient(ClientDTO clientDTO) throws ServiceException {

		String documentType = DocumentType.valueOf(utils.removeHyphen(clientDTO.getDocumentType())).getCode();

		Optional<Client> clientCheck = clientRepository
				.findByIdentificationAndDocumentType(clientDTO.getIdentification(), documentType);

		if (clientCheck.isPresent())
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), THE_CLIENT_ALREADY_EXIST);

		Client client = modelMapper.map(clientDTO, Client.class);
		client.setDocumentType(documentType);
		client.setDisable(false);
		clientRepository.save(client);

		return new ClientResponseDTO("The client was created successfully", clientDTO.getIdentification());
	}

	@Override
	public ClientDTO getClient(String clientIdentification) throws ServiceException {
		Client client = isClientPresent(clientIdentification);

		if (client.isDisable())
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), THE_CLIENT_IS_DISABLED);

		return modelMapper.map(client, ClientDTO.class);
	}

	@Override
	public ClientResponseDTO editClient(ClientDTO clientDTO) throws ServiceException {

		isClientPresent(clientDTO.getIdentification());

		clientRepository.save(modelMapper.map(clientDTO, Client.class));
		return new ClientResponseDTO("The client has been edited", clientDTO.getIdentification());
	}

	@Override
	public ClientResponseDTO deleteClient(String clientIdentification) throws ServiceException {

		Client client = isClientPresent(clientIdentification);

		client.setDisable(true);

		clientRepository.save(client);

		return new ClientResponseDTO("The client has been disabled", client.getIdentification());
	}

	private Client isClientPresent(String clientIdentification) throws ServiceException {

		Optional<Client> client = clientRepository.findById(clientIdentification);

		if (!client.isPresent())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_CLIENT_DOESNT_EXIST);

		return client.get();
	}

}
