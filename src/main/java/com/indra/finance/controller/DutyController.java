package com.indra.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indra.finance.dto.DutyDTO;
import com.indra.finance.dto.DutyResponseDTO;
import com.indra.finance.service.DutyService;

@RestController
public class DutyController {

	@Autowired
	DutyService dutyService;

	@PostMapping("clients")
	public ResponseEntity<DutyResponseDTO> store(@RequestBody DutyDTO dutyDTO) {
		return null;

	}

}
