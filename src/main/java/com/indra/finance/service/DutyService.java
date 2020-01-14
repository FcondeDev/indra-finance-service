package com.indra.finance.service;

import java.util.List;

import com.indra.finance.dto.DutyDTO;
import com.indra.finance.dto.DutyResponseDTO;
import com.indra.finance.exception.ServiceException;

public interface DutyService {

	public DutyResponseDTO saveDuty(DutyDTO dutyDTO);

	public List<DutyDTO> getDuties(Long clientIdentification) throws ServiceException;

	public DutyDTO getDuty(Long dutyId);

	public DutyResponseDTO editDuty(DutyDTO dutyDTO);

	public DutyResponseDTO disableDuty(Long dutyId);
}
