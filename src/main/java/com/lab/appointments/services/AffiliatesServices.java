package com.lab.appointments.services;

import java.util.List;

import com.lab.appointments.model.Affiliates;


public interface AffiliatesServices {

	
	public Affiliates save(Affiliates affiliates);
	
	public Affiliates findById(Integer id);
	
	public void delete(Integer id);
	
	public List<Affiliates> findAll();
	
	public Affiliates update(Affiliates affiliates);
	
}
