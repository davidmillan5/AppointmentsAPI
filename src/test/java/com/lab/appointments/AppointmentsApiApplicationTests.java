package com.lab.appointments;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.lab.appointments.controllers.AffiliatesControllers;
import com.lab.appointments.model.Affiliates;
import com.lab.appointments.model.Appointments;
import com.lab.appointments.model.Tests;
import com.lab.appointments.repository.AffiliatesRepository;
import com.lab.appointments.repository.AppointmentsRepository;
import com.lab.appointments.repository.TestsRepository;
import com.lab.appointments.services.AffiliatesServices;
import com.lab.appointments.services.AppointmentsServices;
import com.lab.appointments.services.TestsServices;


@RunWith(SpringRunner.class)
@SpringBootTest
class AppointmentsApiApplicationTests {

	@Autowired
	private AffiliatesServices affiliatesServices;
	
	
	@Autowired
	private TestsServices testsServices;
	
	
	@Autowired
	private AppointmentsServices appointmentsServices;

	@MockBean
	private AffiliatesRepository affiliatesRepository;
	
	
	@MockBean
	private TestsRepository testsRepository;
	
	@MockBean
	private AppointmentsRepository appointmentsRepository;
	
	
	@Test
	public void findAllAffiliatesTests() {
		when(affiliatesRepository.findAll()).thenReturn(Stream.of(new Affiliates(1, "sofia", 17, "sofia@gmail.com"),
				new Affiliates(2, "Laura", 25, "laura@gmail.com")).collect(Collectors.toList()));
		assertEquals(2, affiliatesServices.findAll().size());
	}

		
	
	@Test
	public void findAllTeststests() {
		when(testsRepository.findAll()).thenReturn(Stream.of(new Tests(10,"Blood","Blood Workd"),
				new Tests(11,"Blood Vitamins","Blood Lab")).collect(Collectors.toList()));
		
		assertEquals(2, testsServices.findAll().size());
	}
	

	
	@Test
	public void saveTestsAffiliates() {
		Affiliates affiliate = new Affiliates(3, "Estefania Osorio", 28, "Estefania@gmail.com");
		when(affiliatesRepository.save(affiliate)).thenReturn(affiliate);
		assertEquals(affiliate, affiliatesServices.save(affiliate));

	}
	

	@Test
	public void saveTestsTests() {
		Tests tests = new Tests(5, "Blood Work", "Full Blood Analysis");
		when(testsRepository.save(tests)).thenReturn(tests);
		assertEquals(tests, testsServices.save(tests));

	}
	
	

	@Test
	public void deleteTestsAffiliates() {
		Affiliates affiliate = new Affiliates(3, "Estefania Osorio", 28, "Estefania@gmail.com");
		affiliatesServices.delete(3);
		verify(affiliatesRepository, times(1)).deleteById(3);

	}

	
	@Test
	public void deleteTestsTests() {
		Tests tests = new Tests(5, "Blood Work", "Full Blood Analysis");
		testsServices.delete(5);
		verify(testsRepository, times(1)).deleteById(5);

	}
	

	
}
