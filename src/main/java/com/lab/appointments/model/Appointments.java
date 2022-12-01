package com.lab.appointments.model;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;

	@NotNull
	@DateTimeFormat(pattern = "hh:mm")
	@JsonFormat(pattern = "hh:mm")
	private Date hour;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "affiliates_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Affiliates affiliates;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tests_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Tests tests;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public Affiliates getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(Affiliates affiliates) {
		this.affiliates = affiliates;
	}

	public Tests getTests() {
		return tests;
	}

	public void setTests(Tests tests) {
		this.tests = tests;
	}

	
	
}
