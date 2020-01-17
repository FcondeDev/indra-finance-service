package com.indra.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.finance.dto.ClientDTO;
import com.indra.finance.dto.ClientResponseDTO;
import com.indra.finance.dto.DutyDTO;
import com.indra.finance.dto.DutyResponseDTO;
import com.indra.finance.dto.PaymentDTO;
import com.indra.finance.dto.PaymentResponseDTO;
import com.indra.finance.helpers.Utils;
import com.indra.finance.model.Duty;
import com.indra.finance.repository.DutyRepository;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("Test for search controller")
@AutoConfigureDataMongo
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class IndraFinanceServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	DutyRepository dutyRepository;

	@SpyBean
	Utils utils;

	@DisplayName("200 Ok SAVE BUSINESS CLIENT")
	@Test
	void verifyClientCreationZ() throws Exception {

		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setIdentification("123543224");
		clientDTO.setDocumentType("TD-004");
		clientDTO.setBusinessName("Alianza Testing SAS");
		clientDTO.setAddress("cra 1 No 2 - 53");
		clientDTO.setPhone(3112435546L);

		String body = mapper.writeValueAsString(clientDTO);
		MvcResult result = mvc.perform(post("/clients").content(body).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		ClientResponseDTO responseDTO = mapper.readValue(result.getResponse().getContentAsString(),
				ClientResponseDTO.class);

		assertEquals("The client was created successfully", responseDTO.getMessage());
		assertNotNull(responseDTO.getClientId());
	}

	@DisplayName("200 Ok SAVE DUTY FOR BUSINESS CLIENT")
	@Test
	void verifyDutyCreationY() throws Exception {

		DutyDTO dutyDTO = new DutyDTO();
		dutyDTO.setClientIdentification("123543224");
		dutyDTO.setDutyName("PR-002");
		dutyDTO.setDutyDescription("Duty For testing");
		dutyDTO.setTotalValue(2000000L);
		dutyDTO.setNumberOfPeriods(8L);
		dutyDTO.setCurrentPeriodValue(250000L);
		dutyDTO.setStatus("RC-001");
		dutyDTO.setCurrentPeriod(1L);

		String body = mapper.writeValueAsString(dutyDTO);
		MvcResult result = mvc.perform(post("/duties").content(body).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		DutyResponseDTO responseDTO = mapper.readValue(result.getResponse().getContentAsString(),
				DutyResponseDTO.class);

		byte nameInBytes[] = responseDTO.getName().getBytes("ISO-8859-1");
		String nameUTF8 = new String(nameInBytes, "UTF-8");

		assertEquals("The duty has been saved", responseDTO.getMessage());
		assertEquals("Tarjeta de Crédito MASTER CARD", nameUTF8);

	}

	@DisplayName("200 Ok PAYMENT")
	@Test
	void verifyPaymentA() throws Exception {

		Optional<List<Duty>> duty = dutyRepository.findByClientIdentificationAndDisable("123543224", false);

		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setBankName("RC-002");
		paymentDTO.setClientIdentification("123543224");
		paymentDTO.setPaymenteDate(new Date());
		paymentDTO.setPaymentPeriod(1L);
		paymentDTO.setPaymentValue(250000L);
		paymentDTO.setDutyId(duty.get().get(0).getDutyId());

		String body = mapper.writeValueAsString(paymentDTO);
		MvcResult result = mvc.perform(post("/payments").content(body).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		PaymentResponseDTO responseDTO = mapper.readValue(result.getResponse().getContentAsString(),
				PaymentResponseDTO.class);

		assertEquals("The payment was registered successfully", responseDTO.getMessage());
		assertNotNull(responseDTO.getName());

	}

	@DisplayName("200 OK MOKING UTILS BEAN")
	@Test
	void mockingABean() throws Exception {

		when(utils.removeHyphen("RC-001")).thenReturn("RC002".toUpperCase());

		DutyDTO dutyDTO = new DutyDTO();
		dutyDTO.setClientIdentification("11148342334");
		dutyDTO.setDutyName("PR-002");
		dutyDTO.setDutyDescription("Duty For testing");
		dutyDTO.setTotalValue(1000000L);
		dutyDTO.setNumberOfPeriods(10L);
		dutyDTO.setCurrentPeriodValue(100000L);
		dutyDTO.setStatus("RC-001");
		dutyDTO.setCurrentPeriod(1L);

		String body = mapper.writeValueAsString(dutyDTO);
		mvc.perform(post("/duties").content(body).contentType(MediaType.APPLICATION_JSON));

		Optional<List<Duty>> duty = dutyRepository.findByClientIdentificationAndDisable("11148342334", false);

		assertEquals("PAGADO", duty.get().get(0).getStatus());

	}

}
