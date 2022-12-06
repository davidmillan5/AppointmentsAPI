package com.lab.appointments.model;

import java.sql.Time;
import java.time.LocalDateTime;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull
	@DateTimeFormat(pattern = "hh:mm")
	@JsonFormat(pattern = "hh:mm")
	@Temporal(TemporalType.TIME)
	private Time hour;

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

	public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
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

	public Appointments() {
	}

	public Appointments(int id, @NotNull Date date, @NotNull Time hour) {
		this.id = id;
		this.date = date;
		this.hour = hour;
	}

	public Appointments(int id, @NotNull Date date, @NotNull Time hour, Affiliates affiliates, Tests tests) {
		this.id = id;
		this.date = date;
		this.hour = hour;
		this.affiliates = affiliates;
		this.tests = tests;
	}

	@Override
	public String toString() {
		return "Appointments [id=" + id + ", date=" + date + ", hour=" + hour + ", affiliates=" + affiliates
				+ ", tests=" + tests + "]";
	}



}
