package com.indra.finance.service.impl;

import static com.indra.finance.error.Error.THE_CLIENT_HAS_NOT_DUTIES;
import static com.indra.finance.error.Error.THE_DUTY_DOESNT_EXIST;
import static com.indra.finance.error.Error.THE_DUTY_IS_DISABLED;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.indra.finance.dto.DutyDTO;
import com.indra.finance.dto.DutyResponseDTO;
import com.indra.finance.exception.ServiceException;
import com.indra.finance.helpers.DutyName;
import com.indra.finance.helpers.DutyState;
import com.indra.finance.helpers.Utils;
import com.indra.finance.model.Duty;
import com.indra.finance.repository.DutyRepository;
import com.indra.finance.service.DutyService;

import lombok.extern.java.Log;

@Service
@Log
public class DutyServiceImpl implements DutyService {

	@Autowired
	DutyRepository dutyRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	Utils utils;

	@Override
	public DutyResponseDTO saveDuty(DutyDTO dutyDTO) {

		String dutyName = DutyName.valueOf(utils.removeHyphen(dutyDTO.getDutyName())).getCode();

		Duty duty = modelMapper.map(dutyDTO, Duty.class);
		duty.setDutyName(dutyName);
		duty.setDutyId(null);
		duty.setStatus(DutyState.valueOf(utils.removeHyphen(dutyDTO.getStatus())).getCode());
		duty.setDisable(false);
		dutyRepository.save(duty);

		log.info("The duty :" + duty.getDutyId() + " was created for the client : " + dutyDTO.getClientIdentification());
		return new DutyResponseDTO("The duty has been saved", dutyName);
	}

	@Override
	public List<DutyDTO> getDuties(String clientIdentification) throws ServiceException {

		Optional<List<Duty>> clientDuties = dutyRepository.findByClientIdentificationAndDisable(clientIdentification,
				false);

		if (!clientDuties.isPresent())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_CLIENT_HAS_NOT_DUTIES);

		List<DutyDTO> dutyListDTO = new ArrayList<>();
		for (Duty duty : clientDuties.get()) {
			dutyListDTO.add(modelMapper.map(duty, DutyDTO.class));
		}

		return dutyListDTO;
	}

	@Override
	public DutyDTO getDuty(String dutyId) throws ServiceException {

		Duty duty = isDutyPresent(dutyId);

		if (duty.isDisable())
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), THE_DUTY_IS_DISABLED);

		return modelMapper.map(duty, DutyDTO.class);
	}

	@Override
	public DutyResponseDTO editDuty(DutyDTO dutyDTO) throws ServiceException {

		isDutyPresent(dutyDTO.getDutyId());

		dutyRepository.save(modelMapper.map(dutyDTO, Duty.class));

		log.info("The duty with id :" + dutyDTO.getDutyId() + " was edited, which belongs to the client : "
				+ dutyDTO.getClientIdentification());

		return new DutyResponseDTO("The duty has been edited", dutyDTO.getDutyName());
	}

	@Override
	public DutyResponseDTO disableDuty(String dutyId) throws ServiceException {

		Duty duty = isDutyPresent(dutyId);

		duty.setDisable(true);

		dutyRepository.save(duty);

		log.info("The duty with id :" + dutyId + " was disable, which belongs to the client : "
				+ duty.getClientIdentification());

		return new DutyResponseDTO("The duty has been disabled", duty.getDutyName());
	}

	private Duty isDutyPresent(String dutyId) throws ServiceException {

		Optional<Duty> duty = dutyRepository.findById(dutyId);

		if (!duty.isPresent())
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), THE_DUTY_DOESNT_EXIST);

		return duty.get();
	}

}
