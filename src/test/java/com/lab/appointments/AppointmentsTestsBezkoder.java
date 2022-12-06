package com.lab.appointments;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.appointments.controllers.AppointmentsControllers;
import com.lab.appointments.model.Affiliates;
import com.lab.appointments.model.Appointments;
import com.lab.appointments.model.Tests;
import com.lab.appointments.repository.AffiliatesRepository;
import com.lab.appointments.repository.AppointmentsRepository;
import com.lab.appointments.repository.TestsRepository;
import com.lab.appointments.services.AffiliatesServices;
import com.lab.appointments.services.AppointmentsServices;
import com.lab.appointments.services.TestsServices;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(AppointmentsControllers.class)
public class AppointmentsTestsBezkoder {

	
	@MockBean
	private AppointmentsServices appointmentsServices;
	
	@MockBean
	private TestsServices testsServices;
	
	@MockBean
	private AffiliatesServices affiliatesServices;
	
	@MockBean
	private AffiliatesRepository affiliatesRepository;
	
	@MockBean
	private TestsRepository testsRepository;
	
	@MockBean
	private AppointmentsRepository appointmentsRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	@Test
	public void saveAppointments() throws Exception {
		
		Date date = Date.valueOf("2022-08-22");
		Time hour = Time.valueOf("03:50:00");
		
		
		Affiliates affiliates = new Affiliates(1,"Sofia Millan",17,"Sofia@gmail.com");
		Tests tests = new Tests(1,"Blood Work","Blood Tests");
		
		
		Appointments appointments = new Appointments();
		appointments.setId(1);
		appointments.setDate(date);
		appointments.setHour(hour);
		appointments.setAffiliates(affiliates);;
		appointments.setTests(tests);
		
		
		Appointments savedAppointment = appointmentsRepository.save(appointments);
		
	}
	
		@Test
	public void getAllObjects() throws Exception {
		mockMvc.perform(get("/api/controller/appointments/getlist/"))
		.andExpect(status().isOk());

	}
	
	
	

}
