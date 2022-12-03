package com.lab.appointments.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.lab.appointments.model.Tests;
import com.lab.appointments.services.TestsServices;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/controller/tests/")
public class TestsControllers {

	@Autowired
	private TestsServices testsServices;

	@PostMapping("/post/")
	public void saveTests(@RequestBody Tests tests) {
		try {
		testsServices.save(tests);
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Tests Not Found",e);
		}
	}
	

	

	@GetMapping("/getlist/")
	public List<Tests> lists() {
		try {
		return testsServices.findAll();
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NO_CONTENT,"Not Tests Content Present",e);
		}
	}

	@GetMapping("/getbyid/{id}")
	public Tests getbyid(@PathVariable Integer id) {
		try {
		return testsServices.findById(id);
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Test Not Found",e);
		}
	}

	@PutMapping("/put/{id}")
	public Tests update(@RequestBody Tests tests, @PathVariable Integer id) {

		try {
		Tests testsUpdated = testsServices.findById(id);

		testsUpdated.setName(tests.getName());
		testsUpdated.setDescription(tests.getDescription());

		return testsServices.save(testsUpdated);
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Test Not Found For Update",e);
		}

	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		try {
		testsServices.delete(id);
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NO_CONTENT,"Not Test Present",e);
		}
	}

}
