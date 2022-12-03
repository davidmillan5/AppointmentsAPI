package com.lab.appointments.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lab.appointments.model.Appointments;


public interface AppointmentsRepository extends JpaRepository<Appointments,Integer> {

	@Query(
			value = "SELECT A FROM Appointments A WHERE A.date =?1 ORDER BY A.affiliates.id"
			)
	List<Appointments> getbydate(Date date);
	
	
}
