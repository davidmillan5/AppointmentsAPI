package com.lab.appointments.controllers;



import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
		try {
			ResponseEntity.status(HttpStatus.OK).body(affliliatesServices.save(affiliates));
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Affiliates Not Found",e);
		}
		
	}

	@GetMapping("/getlist/")
	public List<Affiliates> lists() {
		try {
		return affliliatesServices.findAll();
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NO_CONTENT,"Not Affiliates Content Present",e);
		}
	}

	@GetMapping("/getbyid/{id}")
	public Affiliates getbyid(@PathVariable Integer id) {
		try {
			return affliliatesServices.findById(id);
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Affiliate Not Found",e);
		}
		
	}

	@PutMapping("/put/{id}")
	public Affiliates update(@RequestBody Affiliates affiliates, @PathVariable Integer id) {
		try {
		Affiliates affiliatesUpdated = affliliatesServices.findById(id);

		affiliatesUpdated.setName(affiliates.getName());
		affiliatesUpdated.setAge(affiliates.getAge());
		affiliatesUpdated.setMail(affiliates.getMail());

		return affliliatesServices.save(affiliatesUpdated);
		
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Affiliate Not Found For Update",e);
		}
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		try {
		affliliatesServices.delete(id);
		}catch(Exception e) {
			throw new ResponseStatusException(
					HttpStatus.NO_CONTENT,"Not Affiliate Present",e);
		}
	}
	
	

	

}
