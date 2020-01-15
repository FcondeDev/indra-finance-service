package com.indra.finance.service;

import java.util.List;

import com.indra.finance.dto.DutyDTO;
import com.indra.finance.dto.DutyResponseDTO;
import com.indra.finance.exception.ServiceException;

public interface DutyService {

	public DutyResponseDTO saveDuty(DutyDTO dutyDTO);

	public List<DutyDTO> getDuties(String clientIdentification) throws ServiceException;

	public DutyDTO getDuty(String dutyId) throws ServiceException;

	public DutyResponseDTO editDuty(DutyDTO dutyDTO) throws ServiceException;

	public DutyResponseDTO disableDuty(String dutyId) throws ServiceException;
}
