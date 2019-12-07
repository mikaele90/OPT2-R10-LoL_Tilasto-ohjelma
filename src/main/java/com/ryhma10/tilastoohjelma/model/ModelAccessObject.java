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
	

	//lukee järjestelmän profiilin (creategamedata hyödyntää tätä)
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
	
	//toimii..
	//Lisää pelin tietokantaan
	// vaatii sisäänsä Järjestelmän profiilin nimen, gamedata- , item, team ja additionalolion
	// mitä kukin olio vaatii sisäänsä näkee kyseisen filun konstruktorista
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
	

	@SuppressWarnings( "unchecked")
	@Override
	//lukee tietokannan kaikki pelit profiilista riippumatta
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

	
	// ei tiedä tarviiko vai heivataanko pois, muuta gpm laskuri mahdollisesti jopa vailinainen semmoinen
	public double gpmCalculus(double time, double gold) {
		double result = gold/time;
		double gpm = (double)Math.round(result);
	
		return gpm;
		
	}
	
	//hakee tietokannasta yhden profiilin kaikki pelit
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
	
	//toimii...
	// tätä tuskin tarvitaan...
		@SuppressWarnings("unchecked")
		@Override
		public List<Item> readGamesWithItems() {
			Transaction transaction = null;
			try (Session session = factory.openSession()){
				transaction = session.beginTransaction();
				
				List<Item> results = session.createQuery("Select i FROM Item as i JOIN i.gamedata as g where g.gameid = 1").getResultList();
				
				session.getTransaction().commit();
				session.close();
				return results;
			}catch(Exception e) {
				transaction.rollback();
				throw e;
				}	
		}
		
		//hakee tietokannasta tietyn pelin spesifikaatio datat vaatii parametriksi riotId:n
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
		
		//hakee tietokannasta tietyn pelin molempien puolien 
		//sankarit lukuunottamatta pelaajan omaa koska tämä tieto on normi gamedatassa
		//vaatii parimetriksi riotId:n
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
		
		//hakee yhden pelin gamedata taulun, tästä saa myös itemitkin mikäli haluaa
		//parametriksi vaatii riotId:n
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

		//toimii...
		//hakee kaikki tavarat tietokannasta ei tietoa onko käyttöä
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
		
		//tarkastaa onko kyseinen peli jo tietokannassa
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

}
