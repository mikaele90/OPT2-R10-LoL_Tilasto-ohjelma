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

	/**
	 * Empty constructor
	 */
	public SoftwareProfile() {
		//Default constructor
	}

	/**
	 * The main constructor
	 * @param profileName A String for the SoftwareProfile's name (Required).
	 * @param profilePassword A String for the SoftwareProfile's password, no encryption yet (Required).
	 * @param defaultRegion Optional String for SoftwareProfile's default League of Legends region.
	 * @param defaultLanguage Optional String for SoftwareProfile's default user interface language.
	 *                        Should follow the format of "language_country", e.g. "en_US" or "fi_FI".
	 * @param defaultRiotAccount Optional String for an encrypted version of Riot's accountId. Not in use yet.
	 * @param riotAPIKey Partially optional String for the SoftwareProfile's Riot API-key. Mandatory to get the program
	 *                   working as it should.
	 */
	public SoftwareProfile(String profileName, String profilePassword, String defaultRegion, String defaultLanguage, String defaultRiotAccount, String riotAPIKey) {
		this.profileName = profileName;
		this.profilePassword = profilePassword;
		this.riotAPIKey = riotAPIKey;
		this.defaultRegion = defaultRegion;
		this.defaultLanguage = defaultLanguage;
		this.defaultRiotAccount = defaultRiotAccount;
	}

	/**
	 * Getter for the profileId.
	 * @return The profileId.
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * Setter for the profileId.
	 * @param profileId Sets the profileId.
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	/**
	 * Getter for the profileName.
	 * @return The profileName.
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * Setter for the profileName.
	 * @param profileName Sets the profileName.
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * Getter for the profilePassword.
	 * @return The profilePassword.
	 */
	public String getProfilePassword() {
		return profilePassword;
	}

	/**
	 * Setter for the profilePassword.
	 * @param profilePassword Sets the profilePassword.
	 */
	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}

	/**
	 * Getter for the riotAPIKey.
	 * @return The riotAPIKey.
	 */
	public String getRiotAPIKey() {
		return riotAPIKey;
	}

	/**
	 * Setter for the riotAPIKey.
	 * @param riotAPIKey Sets the riotAPIKey.
	 */
	public void setRiotAPIKey(String riotAPIKey) {
		this.riotAPIKey = riotAPIKey;
	}

	/**
	 * Getter for the profile's defaultRegion.
	 * @return The profile's defaultRegion.
	 */
	public String getDefaultRegion() {
		return defaultRegion;
	}

	/**
	 * Setter for the profile's defaultRegion.
	 * @param defaultRegion Sets the profile's defaultRegion.
	 */
	public void setDefaultRegion(String defaultRegion) {
		this.defaultRegion = defaultRegion;
	}

	/**
	 * Getter for the profile's defaultLanguage.
	 * @return The profile's defaultLanguage.
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * Setter for the profile's defaultLanguage.
	 * @param defaultLanguage Sets the profile's defaultLanguage.
	 */
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * Getter for the encrypted defaultRiotAccount. Not properly implemented yet.
	 * @return The encrypted defaultRiotAccount.
	 */
	public String getDefaultRiotAccount() {
		return defaultRiotAccount;
	}

	/**
	 * Setter for the encrypted defaultRiotAccount. Not properly implemented yet.
	 * @param defaultRiotAccount Sets the encrypted defaultRiotAccount.
	 */
	public void setDefaultRiotAccount(String defaultRiotAccount) {
		this.defaultRiotAccount = defaultRiotAccount;
	}

}