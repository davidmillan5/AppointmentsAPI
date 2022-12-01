package com.lab.appointments.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lab.appointments.model.Appointments;


public interface AppointmentsRepository extends JpaRepository<Appointments,Integer> {

	@Query(
			
			value = "SELECT appointments.date FROM appointments where appointments.date LIKE %:date% group by id_affiliates",
			nativeQuery = true
			)
	List<Appointments> getbydate(@Param("date")Date date);
	
	
}
