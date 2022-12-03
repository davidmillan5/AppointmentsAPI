package com.lab.appointments;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.appointments.controllers.TestsControllers;
import com.lab.appointments.model.Tests;
import com.lab.appointments.services.TestsServices;

@WebMvcTest(TestsControllers.class)
public class TestsTestsBezkoder {

	@MockBean
	private TestsServices testsServices;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void saveTests() throws Exception {
		Tests tests = new Tests(1, "Blood Work", "Full Blood Scan");

		mockMvc.perform(post("/api/controller/tests/post/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tests))).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testsGetList() throws Exception {
		List<Tests> tests = new ArrayList<>(Arrays.asList(new Tests(1, "Blood Work", "Full Blood Scan"),
				new Tests(2, "Enzymes Check", "Check"), new Tests(3, "Retroviral", "Retro look out")));

		when(testsServices.findAll()).thenReturn(tests);
		mockMvc.perform(get("/api/controller/tests/getlist/")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(tests.size())).andDo(print());
	}

	@Test
	public void testsGetById() throws Exception {
		Integer id = 1;
		Tests tests = new Tests(id, "Vitamins check", "Performance Tests");

		when(testsServices.findById(id)).thenReturn(tests);
		mockMvc.perform(get("/api/controller/tests/getbyid/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.name").value(tests.getName()))
				.andExpect(jsonPath("$.description").value(tests.getDescription()));
	}

	@Test
	public void testsUpdate() throws Exception {
		Integer id = 1;

		Tests tests = new Tests(id, "Blood cholesterol test",
				"Cholesterol is a fatty substance mostly created by the liver from the fatty foods in your diet and is vital for the normal functioning of the body.");
		Tests testsUpdated = new Tests(id, "Blood culture",
				"This involves taking a small sample of blood from a vein in your arm and from 1 or more other parts of your body.");

		when(testsServices.findById(id)).thenReturn(tests);
		when(testsServices.save(any(Tests.class))).thenReturn(testsUpdated);

		mockMvc.perform(put("/api/controller/tests/put/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testsUpdated))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(testsUpdated.getName()))
				.andExpect(jsonPath("$.description").value(testsUpdated.getDescription())).andDo(print());

	}

	@Test
	public void testsDelete() throws Exception {
		Integer id = 1;
		doNothing().when(testsServices).delete(id);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/controller/tests/delete/{id}", id))
				.andExpect(status().isOk()).andDo(print());
	}

}
