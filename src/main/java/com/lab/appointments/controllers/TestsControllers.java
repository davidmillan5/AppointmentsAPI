package com.lab.appointments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		testsServices.save(tests);
	}

	@GetMapping("/getlist/")
	public List<Tests> lists() {
		return testsServices.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public Tests getbyid(@PathVariable Integer id) {
		return testsServices.findById(id);
	}

	@PutMapping("/put/{id}")
	public Tests update(@RequestBody Tests tests, @PathVariable Integer id) {

		Tests testsUpdated = testsServices.findById(id);

		testsUpdated.setName(tests.getName());
		testsUpdated.setDescription(tests.getDescription());

		return testsServices.save(testsUpdated);

	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		testsServices.delete(id);
	}

}
