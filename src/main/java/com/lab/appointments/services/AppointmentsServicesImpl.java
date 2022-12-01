package com.lab.appointments.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.appointments.model.Affiliates;
import com.lab.appointments.model.Appointments;
import com.lab.appointments.repository.AffiliatesRepository;
import com.lab.appointments.repository.AppointmentsRepository;

@Service
public class AppointmentsServicesImpl implements AppointmentsServices {

	@Autowired
	private AppointmentsRepository appointmentsRepository;
	
	@Autowired
	private AffiliatesRepository affiliatesRepository;
	
	
	@Override
	@Transactional(readOnly = false)
	public Appointments save(Appointments appointments) {
		return appointmentsRepository.save(appointments);
	}

	@Override
	@Transactional(readOnly = true)
	public Appointments findById(Integer id) {
		return appointmentsRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		appointmentsRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointments> findAll() {
		return (List<Appointments>)appointmentsRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Appointments update(Appointments appointments) {
		return appointmentsRepository.save(appointments);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Appointments> getbydate(Date date) throws Exception {
		try {
			List<Appointments> appointments = appointmentsRepository.getbydate((java.sql.Date) date);
			return appointments;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Optional<Affiliates> getbyidAffiliates(Integer id) {
		return affiliatesRepository.findById(id);
	}

}
