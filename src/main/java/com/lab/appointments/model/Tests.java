package com.lab.appointments.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Tests {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String name;

	@NotNull
	private String description;
	
	
	@OneToMany(mappedBy = "tests", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Appointments> appointments = new HashSet<>();


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Appointments> getAppointments() {
		return appointments;
	}


	public void setAppointments(Set<Appointments> appointments) {
		this.appointments = appointments;
	}


	public Tests() {
	}


	public Tests(int id, @NotNull String name, @NotNull String description, Set<Appointments> appointments) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.appointments = appointments;
	}


	public Tests(@NotNull String name, @NotNull String description, Set<Appointments> appointments) {
		this.name = name;
		this.description = description;
		this.appointments = appointments;
	}


	public Tests(int id, @NotNull String name, @NotNull String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}


	@Override
	public String toString() {
		return "Tests [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
	
}
