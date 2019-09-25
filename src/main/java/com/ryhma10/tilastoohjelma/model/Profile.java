package com.ryhma10.tilastoohjelma.model;

import java.util.*;
import javax.persistence.*;


@Entity
@Table (name="Profile")


public class Profile {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="Psw")
	private String psw;
	
//	@OneToMany(mappedBy="profile", cascade = CascadeType.ALL)
//	private Set<Gamedata> gamedata = new HashSet <Gamedata>;


	public Profile() {
	}
	
	public Profile(int id, String name, String psw) {
		this.id = id;
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

//	public Set<Gamedata> getGamedata() {
//		return gamedata;
//	}
//
//	public void setGamedata(Set<Gamedata> gamedata) {
//		this.gamedata = gamedata;
//	}
//	
}