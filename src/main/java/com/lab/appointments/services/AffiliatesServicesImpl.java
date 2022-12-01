package com.lab.appointments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.appointments.model.Affiliates;
import com.lab.appointments.repository.AffiliatesRepository;

@Service
public class AffiliatesServicesImpl implements AffiliatesServices {

	@Autowired
	private AffiliatesRepository affiliatesRepository;
	
	
	@Override
	@Transactional(readOnly = false)
	public Affiliates save(Affiliates affiliates) {
		return affiliatesRepository.save(affiliates);
	}

	@Override
	@Transactional(readOnly = true)
	public Affiliates findById(Integer id) {
		return affiliatesRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		affiliatesRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Affiliates> findAll() {
		return (List<Affiliates>) affiliatesRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Affiliates update(Affiliates affiliates) {
		return affiliatesRepository.save(affiliates);
	}

}
