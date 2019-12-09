package com.ryhma10.tilastoohjelma.model;

import org.hibernate.boot.registry.*;

import java.util.*;
import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;
import java.lang.Math;


public class ModelAccessObject implements IModelDAO {
	
	SessionFactory factory= null;
	
	public ModelAccessObject() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			
		try{
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Istuntotehtaan luonti ei onnistunut");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
			}
	}
	
	
	
	/**
	 * readProfile method read SoftareProfile data from database.
	 * @param Takes in the profile name in String format.
	 * @return SoftwareProfile object
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public SoftwareProfile readProfile(String profileName) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Query q = session.createQuery("FROM SoftwareProfile WHERE profileName = :profileName");
			q.setParameter("profileName", profileName);
			SoftwareProfile profile = (SoftwareProfile) q.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return profile;
		}catch(Exception e) {
			transaction.rollback();
			throw e;
		}
	}
	
	
	/**
	 * createGamedata method adds all the gamedata to database. It adds data to Gamedata- , Item-,
	 * Team-, and Additional tables.
	 * @param Sets SoftwareProfile by profiles name parameter in String format
	 * @param Sets Gamedata teable via Gamedata object
	 * @param Sets Item table via Item object
	 * @param Sets Team table via Team object
	 * @param Sets Additional table via Additional object
	 * @return boolean true or false.
	 */
		@Override
		public boolean createGamedata(String name, Gamedata gamedata, Item item, Team team, Additional additional) {
			Transaction transaction = null;
			boolean totuus = false;
			try(Session session = factory.openSession()) {
				transaction = session.beginTransaction();
				SoftwareProfile profile = readProfile(name);
				Gamedata game = gamedata;
				game.setSoftwareProfile(profile);
				Item newItem = item;
				game.setItem(newItem);
				newItem.setGamedata(game);
				Team newTeam = team;
				Additional newData = additional;
				newTeam.setGamedata(game);
				newData.setGamedata(game);
				session.saveOrUpdate(game);
				session.saveOrUpdate(newItem);
				session.saveOrUpdate(newTeam);
				session.saveOrUpdate(newData);
				transaction.commit();
				session.close();
				totuus = true;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
			}
			return totuus;
		}
	
	
	/**
	 * readGames method lists all the games from database.
	 * @return list of Gamedata objects
	 */
	@SuppressWarnings( "unchecked")
	@Override
	public List<Gamedata> readGames() {
		Transaction transaction = null;
		try (Session session = factory.openSession()){
			transaction = session.beginTransaction();
			
			List<Gamedata> games = session.createQuery("FROM Gamedata").getResultList();
			
			session.getTransaction().commit();
			session.close();
			return games;
		}catch(Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	
	/**
	 * gpmCalculus counts simply gold per minute value.
	 * @param Takes in time as double format
	 * @param Takes in gold value in double format.
	 * @return Answer of the calculus in double format.
	 */
	public double gpmCalculus(double time, double gold) {
		double result = gold/time;
		double gpm = (double)Math.round(result);
	
		return gpm;
		
	}
	
	/**
	 * readSpecificProfilesGames method returns all the games which were played by same profile.
	 * @param Method takes in Profile name in String format.
	 * @return list of gamedata objects
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gamedata> readSpecificProfilesGames(String name) {
		Transaction transaction = null;
		try(Session session = factory.openSession()){
			transaction = session.beginTransaction();

			List<Gamedata> results = session.createQuery("select g FROM  Gamedata as g Join g.SoftwareProfile as p where p.profileName = :name").setParameter("name", name).getResultList();
			session.getTransaction().commit();
			session.close();
			return results;
		}catch(Exception e) {
			transaction.rollback();
			throw e;
			}
	}
	
	/**
	 * addProfile method add SoftwareProfile data into database.
	 * @param Method takes in SoftwareProfile object and saves it to the database.
	 * @return boolean true or false.
	 */
	@Override
	public boolean addProfile(SoftwareProfile profile) {
		Transaction transaction = null;
		boolean totuus = false;
		try(Session session = factory.openSession()){
			transaction = session.beginTransaction();
			session.saveOrUpdate(profile);
			session.getTransaction().commit();
			session.close();
			totuus = true;
		}catch (Exception e) {
			transaction.rollback();
			throw e;
		}
			
		return totuus;
		}

	/**
	 * Creates a SoftwareProfile into the database.
	 * @param profileName Sets the name of the SoftwareProfile.
	 * @param profilePassword Sets the password of the SoftwareProfile.
	 * @param defaultRegion Sets the region of the SoftwareProfile.
	 * @param defaultLanguage Sets the language of the SoftwareProfile.
	 * @param defaultRiotAccount Sets the riot account of the SoftwareProfile. Not in use currently.
	 * @param riotAPIKey Sets the Riot API-key of the SoftwareProfile.
	 * @return The resulting string.
	 */
	@Override
	public String createProfile(String profileName, String profilePassword, String defaultRegion, String defaultLanguage, String defaultRiotAccount, String riotAPIKey) {
		SoftwareProfile newProfile;
		String resultString;
		Transaction transaction = null;
		try {
			Session session = factory.openSession();
			Query query = session.createQuery("FROM SoftwareProfile WHERE profileName = :profileName");
			query.setParameter("profileName", profileName);
			List queryResult = query.list();
			if (queryResult.isEmpty()) {
				transaction = session.beginTransaction();
				newProfile = new SoftwareProfile(profileName, profilePassword, defaultRegion, defaultLanguage, defaultRiotAccount, riotAPIKey);
				session.saveOrUpdate(newProfile);
				transaction.commit();
				factory.close();
				resultString = "Profile successfully created";
			}
			else {
				System.out.println("Failed to create profile. Probably too many records found.");
				resultString = "Profile already exists";
			}
		} catch (Exception e) {
			resultString = "Database connection error";
			System.out.println("Failed to create profile.");
			if (transaction != null && factory != null) {
				transaction.rollback();
			}
			if (factory != null) {
				factory.close();
			}
			e.printStackTrace();
			return resultString;
		}
		if (factory.isOpen()) {
			factory.close();
		}
		return resultString;
	}

	/**
	 * Attempts to login a SoftwareProfile.
	 * @param profileName The name of the SoftwareProfile.
	 * @param profilePassword The password of the SoftwareProfile.
	 * @return The resulting string.
	 */
	@Override
	public String loginProfile(String profileName, String profilePassword) {
		String resultString;
		try {
			Session session = factory.openSession();
			Query query = session.createQuery("FROM SoftwareProfile WHERE profileName = :profileName AND profilePassword = :profilePassword");
			query.setParameter("profileName", profileName);
			query.setParameter("profilePassword", profilePassword);
			List queryResult = query.list();
			if (queryResult.size() == 1) {
				resultString = "Login successful";
			}
			else if (queryResult.isEmpty()){
				resultString = "Profile not found";
			}
			else {
				resultString = "Too many records found";
			}
		} catch (Exception e) {
			resultString = "Database connection error";
			System.out.println("Database connection error (loginProfile)");
			if (factory != null) {
				factory.close();
			}
			e.printStackTrace();
			return resultString;
		}
		if (factory.isOpen()) {
			factory.close();
		}
		return resultString;
	}

	/**
	 * Sets the SoftwareProfile, designed for use with loginProfile-method.
	 * @param profileName The name of the SoftwareProfile.
	 * @param profilePassword The password of the SoftwareProfile.
	 * @return The SoftwareProfile.
	 */
	@Override
	public SoftwareProfile setLoggedInProfile(String profileName, String profilePassword) {
		SoftwareProfile loggedInProfile = null;
		try {
			Session session = factory.openSession();
			Query query = session.createQuery("FROM SoftwareProfile WHERE profileName = :profileName AND profilePassword = :profilePassword");
			query.setParameter("profileName", profileName);
			query.setParameter("profilePassword", profilePassword);
			List queryResult = query.list();
			System.out.println(queryResult);
			if (queryResult.size() == 1) {
				loggedInProfile = (SoftwareProfile)queryResult.get(0);
				factory.close();
			}
			else {
				System.out.println("Fail (setLoggedInProfile)");
			}
		} catch (Exception e) {
			if (factory != null) {
				factory.close();
			}
			e.printStackTrace();
			return loggedInProfile;
		}
		if (factory.isOpen()) {
			factory.close();
		}
		return loggedInProfile;
	}

	/**
	 * Updates one SoftwareProfile.
	 * @param currentProfile The currently loaded SoftwareProfile.
	 * @param updateDefaultRegion If true, updates the SoftwareProfile's defaultRegion.
	 * @param updateDefaultLanguage If true, updates the SoftwareProfile's defaultLanguage.
	 * @return True, on success.
	 */
	@Override
	public boolean updateProfile(SoftwareProfile currentProfile, boolean updateDefaultRegion, boolean updateDefaultLanguage) {
		boolean success = false;
		Transaction transaction = null;
		try {
			Session session = factory.openSession();
			transaction = session.beginTransaction();
			SoftwareProfile placeholderProfile = session.load(SoftwareProfile.class, currentProfile.getProfileId());
			placeholderProfile.setRiotAPIKey(currentProfile.getRiotAPIKey());
			if (updateDefaultRegion) {
				placeholderProfile.setDefaultRegion(currentProfile.getDefaultRegion());
			}
			if (updateDefaultLanguage) {
				placeholderProfile.setDefaultLanguage(currentProfile.getDefaultLanguage());
			}
			placeholderProfile.setProfilePassword(currentProfile.getProfilePassword());
			placeholderProfile.setDefaultRiotAccount(null);
			session.update(placeholderProfile);
			session.getTransaction().commit();
			success = true;
			session.close();
		} catch (Exception e) {
			if (factory != null && transaction != null) {
				transaction.rollback();
			}
			if (factory != null) {
				factory.close();
			}
			e.printStackTrace();
			return success;
		}
		if (factory.isOpen()) {
			factory.close();
		}
		return success;
	}
		
		/**
		 * readAdditionalData method searches single games all the extra data.
		 * @param Method takes in riot id and searches with it from the database.
		 * @return Additional object.
		 */
		@SuppressWarnings("unchecked")
		public Additional readAdditionalData(long riotid){
			Transaction transaction = null;
			try(Session session = factory.openSession()){
				transaction = session.beginTransaction();
				
				Additional result = (Additional) session.createQuery("Select a from Additional as a Join a.gamedata as g where g.riotid = :id").setParameter("id", riotid).uniqueResult();
				session.getTransaction().commit();
				return result;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}	
		}
		
		/**
		 * readTeamComposition method searches both own team and enemy team champions from a single game.
		 * @param Method takes in riot id
		 * @return Team object
		 */
		@SuppressWarnings("unchecked")
		public Team readTeamComposition(long riotid){
			Transaction transaction = null;
			try(Session session = factory.openSession()){
				transaction = session.beginTransaction();
				
				Team result = (Team) session.createQuery("Select t from Team as t Join t.gamedata as g where g.riotid = :id").setParameter("id", riotid).uniqueResult();
				session.getTransaction().commit();
				return result;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}	
		}
		
		/**
		 * readOneGame method searches one game specific game from database.
		 * @param Method takes in riot id.
		 * @return Gamedata object.
		 */
		@Override
		@SuppressWarnings("rawtypes")
		public Gamedata readOneGame(long riotid) {
			Transaction transaction = null;
			try(Session session = factory.openSession()) {
				transaction = session.beginTransaction();
				Query q = session.createQuery("FROM Gamedata WHERE riotid = :riotid");
				q.setParameter("riotid", riotid);
				Gamedata gamedata = (Gamedata) q.uniqueResult();
				session.getTransaction().commit();
				session.close();
				return gamedata;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
			}
		}

		/**
		 * readItems method lists every single item from database.
		 * @return List of Item objects
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Item> readItems() {
			Transaction transaction = null;
			try (Session session = factory.openSession()){
				transaction = session.beginTransaction();
				
				List<Item> items = session.createQuery("FROM Item").getResultList();
				
				session.getTransaction().commit();
				session.close();
				return items;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}
		}
		
		/**
		 * checkGame method checks from database if gamedata already exists in there.
		 * @param Method takes in riot id.
		 * @return boolean true or false
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public boolean checkGame(long riotid) {
			Transaction transaction = null;
			try(Session session = factory.openSession()) {
				transaction = session.beginTransaction();
				Query q = session.createQuery("FROM Gamedata WHERE riotid = :riotid");
				q.setParameter("riotid", riotid);
				Gamedata gamedata = (Gamedata) q.uniqueResult();
				session.getTransaction().commit();
				session.close();
				if (gamedata !=null) {
					return true;
				}
				else {
					return false;
				}
			}catch(Exception e) { 
				transaction.rollback();
				throw e;
			}
		}
		
		/**
		 * deleteAdditional method deletes all columns from additional table
		 * @return boolean true or false
		 */
		public boolean deleteAdditonal() {
			Transaction transaction = null;
			boolean truth = false;
			
			try(Session session = factory.openSession()){
				transaction = session.beginTransaction();
				String hql = "delete Additional";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				session.getTransaction().commit();
				truth = true;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}
			return truth;	
		}
		
		/**
		 * deleteTeam method deletes all columns from team table
		 * @return boolean true or false
		 */
		public boolean deleteTeam() {
			Transaction transaction = null;
			boolean truth = false;
			
			try(Session session = factory.openSession()){
				transaction = session.beginTransaction();
				String hql = "delete Team";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				session.getTransaction().commit();
				truth = true;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}
			return truth;	
		}
		
		/**
		 * deleteItem method deletes all columns from item table
		 * @return boolean true or false
		 */
		public boolean deleteItem() {
			Transaction transaction = null;
			boolean truth = false;
			
			try(Session session = factory.openSession()){
				transaction = session.beginTransaction();
				String hql = "delete Item";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				session.getTransaction().commit();
				truth = true;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}
			return truth;	
		}
		
		/**
		 * deleteGamedata method deletes all columns from gamedata table
		 * @return boolean true or false
		 */
		public boolean deleteGamedata() {
			Transaction transaction = null;
			boolean truth = false;
			
			try(Session session = factory.openSession()){
				transaction = session.beginTransaction();
				String hql = "delete Gamedata";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				session.getTransaction().commit();
				truth = true;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}
			return truth;	
		}
		
		/**
		 * deleteProfile method deletes all columns from softwareprofile table
		 * @return boolean true or false
		 */
		@SuppressWarnings("null")
		public boolean deleteProfile() {
			Transaction transaction = null;
			boolean truth = false;
			
			try(Session session = factory.openSession()){
				transaction = session.beginTransaction();
				String hql = "delete SoftwareProfile";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				session.getTransaction().commit();
				truth = true;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}
			return truth;	
		}

}
