package com.lab.appointments.controllers;

import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lab.appointments.model.Affiliates;
import com.lab.appointments.model.Appointments;
import com.lab.appointments.model.Tests;
import com.lab.appointments.repository.AffiliatesRepository;
import com.lab.appointments.repository.AppointmentsRepository;
import com.lab.appointments.repository.TestsRepository;
import com.lab.appointments.services.AffiliatesServices;
import com.lab.appointments.services.AppointmentsServices;
import com.lab.appointments.services.TestsServices;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/controller/appointments/")
public class AppointmentsControllers {

	@Autowired
	public AppointmentsServices appointmentsServices;

	@Autowired
	private TestsServices testsServices;

	@Autowired
	private AffiliatesServices affiliatesServices;

	@Autowired
	public AffiliatesRepository affiliatesRepository;

	@Autowired
	public TestsRepository testsrepository;

	@Autowired
	public AppointmentsRepository appointmentsRepository;

	@PostMapping("/post/")
	public ResponseEntity<Appointments> saveAppointments(@Valid @RequestBody Appointments appointments) {
		Optional<Affiliates> affiliatesOptional = affiliatesRepository.findById(appointments.getAffiliates().getId());
		Optional<Tests> testsOptional = testsrepository.findById(appointments.getTests().getId());

		if (!affiliatesOptional.isPresent() && !testsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		appointments.setAffiliates(affiliatesOptional.get());
		appointments.setTests(testsOptional.get());

		Appointments saveAppointments = appointmentsServices.save(appointments);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveAppointments.getId()).toUri();

		return ResponseEntity.created(location).body(saveAppointments);

	}

	@GetMapping("/getlist/")
	public List<Appointments> lists() {
		return appointmentsServices.findAll();
	}

	@PutMapping("/put/{id}")
	public ResponseEntity<Appointments> updateApointments(@Valid @RequestBody Appointments appointments,
			@PathVariable Integer id) {
		Optional<Affiliates> affiliatesOptional = affiliatesRepository.findById(appointments.getAffiliates().getId());
		Optional<Tests> testsOptional = testsrepository.findById(appointments.getTests().getId());

		if (!affiliatesOptional.isPresent() && !testsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Optional<Appointments> appointmentsOptional = appointmentsRepository.findById(id);

		if (!appointmentsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		appointments.setAffiliates(affiliatesOptional.get());
		appointments.setTests(testsOptional.get());

		appointments.setId(appointmentsOptional.get().getId());

		appointmentsRepository.save(appointments);

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Appointments> deleteAppointment(@PathVariable Integer id) {
		Optional<Appointments> appointmentsOptional = appointmentsRepository.findById(id);

		if (!appointmentsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		appointmentsRepository.delete(appointmentsOptional.get());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/getbyid/{id}")
	public Appointments getbyid(@PathVariable Integer id) {
		return appointmentsServices.findById(id);
	}

	@GetMapping("/getbyaffiliates/{id}")
	public Optional<Affiliates> getbyidAffiliates(@PathVariable Integer id) {
		return affiliatesRepository.findById(id);
	}
	
	@GetMapping("/getbydate/{date}")
	public ResponseEntity<?>getbydate(@RequestParam Date date){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(appointmentsRepository.getbydate(date));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \""+ e.getMessage()+"\"}"));
		}
	}
	
	

}
