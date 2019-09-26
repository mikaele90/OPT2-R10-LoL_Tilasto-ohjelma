package com.ryhma10.tilastoohjelma.model;

import javax.persistence.*;


@Entity
@Table (name="Profile")


public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="Psw")
	private String psw;
	
	
	public Profile() {
	}
	
	public Profile(String name, String psw) {
		this.name = name;
		this.psw = psw;
	}

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

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

}