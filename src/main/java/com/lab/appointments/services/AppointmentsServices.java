package com.lab.appointments.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.lab.appointments.model.Affiliates;
import com.lab.appointments.model.Appointments;

public interface AppointmentsServices {

	public Appointments save(Appointments appointments);
	
	public Appointments findById(Integer id);
	
	public void delete(Integer id);
	
	public List<Appointments> findAll();
	
	public Appointments update(Appointments appointments);
	
	List<Appointments> getbydate(Date date) throws Exception;
	
	public Optional<Affiliates> getbyidAffiliates(@PathVariable Integer id);
	
	
}
