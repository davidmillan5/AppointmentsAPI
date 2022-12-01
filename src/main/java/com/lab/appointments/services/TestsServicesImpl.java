package com.lab.appointments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.appointments.model.Tests;
import com.lab.appointments.repository.TestsRepository;

@Service
public class TestsServicesImpl implements TestsServices {

	@Autowired
	public TestsRepository testsRepository;
	
	
	@Override
	@Transactional(readOnly = false)
	public Tests save(Tests tests) {
		return testsRepository.save(tests);
	}

	@Override
	@Transactional(readOnly = true)
	public Tests findById(Integer id) {
		return testsRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		testsRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tests> findAll() {
		return (List<Tests>)testsRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Tests update(Tests tests) {
		return testsRepository.save(tests);
	}

}
