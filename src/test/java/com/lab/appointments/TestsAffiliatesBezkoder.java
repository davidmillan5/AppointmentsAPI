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
import com.lab.appointments.controllers.AffiliatesControllers;
import com.lab.appointments.model.Affiliates;
import com.lab.appointments.repository.AffiliatesRepository;
import com.lab.appointments.services.AffiliatesServices;

@WebMvcTest(AffiliatesControllers.class)
public class TestsAffiliatesBezkoder {

	@MockBean
	private AffiliatesServices affiliatesServices;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void saveAffiliate() throws Exception {
		Affiliates affiliates = new Affiliates(1, "Sofia Millan", 17, "sofia@gmail.com");

		mockMvc.perform(post("/api/controller/affiliates/post/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(affiliates))).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getlist() throws Exception {
		List<Affiliates> affiliates = new ArrayList<>(
				Arrays.asList(new Affiliates(1, "Sofia Millan", 17, "sogia@gmail.com"),
						new Affiliates(2, "Estefania Osorio", 28, "estefa@gmail.com"),
						new Affiliates(3, "Laura Osorio", 25, "lau@gmail.com")));

		when(affiliatesServices.findAll()).thenReturn(affiliates);
		mockMvc.perform(get("/api/controller/affiliates/getlist/")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(affiliates.size())).andDo(print());
	}

	@Test
	public void getbyId() throws Exception {
		Integer id = 1;
		Affiliates affiliates = new Affiliates(id, "Sofia Millan", 17, "sofia@gmail.com");

		when(affiliatesServices.findById(id)).thenReturn(affiliates);
		mockMvc.perform(get("/api/controller/affiliates/getbyid/{id}", id)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value(affiliates.getName()))
				.andExpect(jsonPath("$.mail").value(affiliates.getMail()))
				.andExpect(jsonPath("$.age").value(affiliates.getAge())).andDo(print());
	}

	@Test
	public void update() throws Exception{
		Integer id = 1;
		
		Affiliates affiliates = new Affiliates(id, "Sofia Millan", 17, "sofia@gmail.com");
		Affiliates affiliatesUpdated = new Affiliates(id,"Estefania Osorio",25,"Estefania@gmail.com");
		
		when(affiliatesServices.findById(id)).thenReturn(affiliates);
		when(affiliatesServices.save(any(Affiliates.class))).thenReturn(affiliatesUpdated);
		
		 mockMvc.perform(put("/api/controller/affiliates/put/{id}", id).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(affiliatesUpdated)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(affiliatesUpdated.getName()))
        .andExpect(jsonPath("$.age").value(affiliatesUpdated.getAge()))
        .andExpect(jsonPath("$.mail").value(affiliatesUpdated.getMail()))
        .andDo(print());
		
	}
	
	@Test
	public void delete() throws Exception{
		Integer id = 1;
		doNothing().when(affiliatesServices).delete(id);
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/controller/affiliates/delete/{id}", id))
         .andExpect(status().isOk())
         .andDo(print());
	}
	
	
	
}
