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

import com.lab.appointments.model.Affiliates;
import com.lab.appointments.services.AffiliatesServices;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/controller/affiliates/")
public class AffiliatesControllers {

	@Autowired
	private AffiliatesServices affliliatesServices;

	@PostMapping("/post/")
	public void saveAffiliate(@RequestBody Affiliates affiliates) {
		affliliatesServices.save(affiliates);
	}

	@GetMapping("/getlist/")
	public List<Affiliates> lists() {
		return affliliatesServices.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public Affiliates getbyid(@PathVariable Integer id) {
		return affliliatesServices.findById(id);
	}

	@PutMapping("/put/{id}")
	public Affiliates update(@RequestBody Affiliates affiliates, @PathVariable Integer id) {

		Affiliates affiliatesUpdated = affliliatesServices.findById(id);

		affiliatesUpdated.setName(affiliates.getName());
		affiliatesUpdated.setAge(affiliates.getAge());
		affiliatesUpdated.setMail(affiliates.getMail());

		return affliliatesServices.save(affiliatesUpdated);

	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		affliliatesServices.delete(id);
	}

}
