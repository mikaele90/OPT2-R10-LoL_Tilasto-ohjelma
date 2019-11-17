package com.ryhma10.tilastoohjelma.model;

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
			System.exit(-1);
			}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public SoftwareProfile readProfile(String profileName) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {;
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
	
	@Override
	public boolean createGamedata(Gamedata gamedata) {
		Transaction transaction = null;
		boolean totuus = false;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(gamedata);
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
	
	public List<Gamedata> readGames() {
		Transaction transaction = null;
		try (Session session = factory.openSession()){
			transaction = session.beginTransaction();
			
			List<Gamedata> games = session.createQuery("FROM Gamedata ORDER BY gameid DESC").getResultList();
			
			session.getTransaction().commit();
			session.close();
			return games;
			}catch(Exception e) {
			transaction.rollback();
			throw e;
			}
			
	}
	
	
	public double gpmCalculus(double time, double gold) {
		double result = gold/time;
		double gpm = (double)Math.round(result);
	
		return gpm;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gamedata> readSpesificGames(String profileName) {
		Transaction transaction = null;
		try (Session session = factory.openSession()){
			transaction = session.beginTransaction();
			List<Gamedata> games = session.createQuery("FROM Gamedata WHERE pname = :pname ORDER BY gameid DESC").setParameter("pname", profileName).getResultList();
			session.getTransaction().commit();
			session.close();
			return games;
			}catch(Exception e) {
			transaction.rollback();
			throw e;
			}
	}
	
	

	@Override
	public boolean deleteGame(int gameid) {
		Transaction transaction = null;
		Gamedata game = new Gamedata();
		boolean totuus = false;
		try(Session session = factory.openSession()){
			transaction = session.beginTransaction();
			session.load(game, gameid);
			session.delete(game);
			session.getTransaction().commit();
			session.close();
			totuus = true;
		}catch (Exception e) {
			transaction.rollback();
		}
			
		return totuus;
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

	public SoftwareProfile setLoggedInProfile(String profileName, String profilePassword) {
		SoftwareProfile loggedInProfile = null;
		Transaction transaction = null;
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
 
}	