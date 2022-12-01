package com.lab.appointments.model;



import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Affiliates {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String name;

	@NotNull
	private int age;

	@NotNull
	private String mail;

	@OneToMany(mappedBy = "affiliates")
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set<Appointments> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointments> appointments) {
		this.appointments = appointments;
	}

	
	

}
