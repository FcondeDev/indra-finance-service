package com.indra.finance.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.finance.dto.DutyDTO;
import com.indra.finance.dto.DutyResponseDTO;
import com.indra.finance.exception.ServiceException;
import com.indra.finance.service.DutyService;

@RestController
public class DutyController {

	@Autowired
	DutyService dutyService;

	@PostMapping("duties")
	public ResponseEntity<DutyResponseDTO> store(@RequestBody @Validated DutyDTO dutyDTO) {
		return new ResponseEntity<>(dutyService.saveDuty(dutyDTO), HttpStatus.OK);

	}

	@GetMapping("duties")
	public ResponseEntity<List<DutyDTO>> index(@RequestParam(required = true) String clientIdentification)
			throws ServiceException {
		return new ResponseEntity<>(dutyService.getDuties(clientIdentification), HttpStatus.OK);

	}

	@GetMapping("duties/{dutyId}")
	public ResponseEntity<DutyDTO> show(@PathVariable String dutyId) throws ServiceException {
		return new ResponseEntity<>(dutyService.getDuty(dutyId), HttpStatus.OK);

	}

	@PutMapping("duties")
	public ResponseEntity<DutyResponseDTO> update(@RequestBody @Validated DutyDTO dutyDTO) throws ServiceException {
		return new ResponseEntity<>(dutyService.editDuty(dutyDTO), HttpStatus.OK);

	}

	@DeleteMapping("duties/{dutyId}")
	public ResponseEntity<DutyResponseDTO> destroy(@PathVariable String dutyId) throws ServiceException {
		return new ResponseEntity<>(dutyService.disableDuty(dutyId), HttpStatus.OK);

	}

}
