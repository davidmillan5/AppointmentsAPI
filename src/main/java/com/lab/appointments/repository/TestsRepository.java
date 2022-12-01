package com.lab.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.appointments.model.Tests;



public interface TestsRepository extends JpaRepository<Tests,Integer> {

}
