package com.indra.finance.service.impl;

import static com.indra.finance.error.Error.THE_CLIENT_HAS_NOT_DUTIES;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.indra.finance.dto.DutyDTO;
import com.indra.finance.dto.DutyResponseDTO;
import com.indra.finance.exception.ServiceException;
import com.indra.finance.helpers.DutyState;
import com.indra.finance.model.Duty;
import com.indra.finance.repository.DutyRepository;
import com.indra.finance.service.DutyService;

@Service
public class DutyServiceImpl implements DutyService {

	@Autowired
	DutyRepository dutyRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DutyResponseDTO saveDuty(DutyDTO dutyDTO) {
		Duty duty = modelMapper.map(dutyDTO, Duty.class);
		duty.setStatus(DutyState.valueOf(dutyDTO.getStatus()).getCode());
		dutyRepository.save(duty);
		return new DutyResponseDTO("The duty has been saved", dutyDTO.getClientIdentification());
	}

	@Override
	public List<DutyDTO> getDuties(Long clientIdentification) throws ServiceException {
		Optional<List<Duty>> clientDuties = dutyRepository.findByClientIdentification(clientIdentification);

		if (!clientDuties.isPresent())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_CLIENT_HAS_NOT_DUTIES);

		return modelMapper.map(clientDuties, new TypeToken<List<DutyDTO>>() {
		}.getType());
	}

	@Override
	public DutyDTO getDuty(Long dutyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DutyResponseDTO editDuty(DutyDTO dutyDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DutyResponseDTO disableDuty(Long dutyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
