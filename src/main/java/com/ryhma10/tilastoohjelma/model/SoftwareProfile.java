package com.ryhma10.tilastoohjelma.model;

import javax.persistence.*;

@Entity
@Table (name="SoftwareProfile")

public class SoftwareProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "profileId")
	private int profileId;

	@Column(name = "profileName")
	private String profileName;

	@Column(name = "profilePassword")
	private String profilePassword;

	@Column(name = "riotAPIKey")
	private String riotAPIKey;

	@Column(name = "defaultRegion")
	private String defaultRegion;

	@Column(name = "defaultLanguage")
	private String defaultLanguage;

	@Column(name = "defaultRiotAccount")
	private String defaultRiotAccount;

	public SoftwareProfile() {
		//Default constructor
	}

	public SoftwareProfile(String profileName, String profilePassword, String defaultRegion, String defaultLanguage, String defaultRiotAccount, String riotAPIKey) {
		this.profileName = profileName;
		this.profilePassword = profilePassword;
		this.riotAPIKey = riotAPIKey;
		this.defaultRegion = defaultRegion;
		this.defaultLanguage = defaultLanguage;
		this.defaultRiotAccount = defaultRiotAccount;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfilePassword() {
		return profilePassword;
	}

	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}

	public String getRiotAPIKey() {
		return riotAPIKey;
	}

	public void setRiotAPIKey(String riotAPIKey) {
		this.riotAPIKey = riotAPIKey;
	}

	public String getDefaultRegion() {
		return defaultRegion;
	}

	public void setDefaultRegion(String defaultRegion) {
		this.defaultRegion = defaultRegion;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getDefaultRiotAccount() {
		return defaultRiotAccount;
	}

	public void setDefaultRiotAccount(String defaultRiotAccount) {
		this.defaultRiotAccount = defaultRiotAccount;
	}

}