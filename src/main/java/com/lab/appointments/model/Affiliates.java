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

	@OneToMany(mappedBy = "affiliates", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public Affiliates() {
		
	}

	public Affiliates(int id, @NotNull String name, @NotNull int age, @NotNull String mail,
			Set<Appointments> appointments) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.mail = mail;
		this.appointments = appointments;
	}

	public Affiliates(@NotNull String name, @NotNull int age, @NotNull String mail) {
		this.name = name;
		this.age = age;
		this.mail = mail;
	}

	public Affiliates(int id, @NotNull String name, @NotNull int age, @NotNull String mail) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.mail = mail;
	}

	public Affiliates(@NotNull String name, @NotNull int age, @NotNull String mail, Set<Appointments> appointments) {
		this.name = name;
		this.age = age;
		this.mail = mail;
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Affiliates [id=" + id + ", name=" + name + ", age=" + age + ", mail=" + mail + "]";
	}

	
	
	

}
