package com.ryhma10.tilastoohjelma.model;

import org.hibernate.boot.registry.*;

import java.util.*;
import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;


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
	
	
	//toimii...
	@SuppressWarnings("rawtypes")
	@Override
	public Profile readProfile(String name) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {;
			transaction = session.beginTransaction();
			Query q = session.createQuery("FROM Profile WHERE name = :name");
			q.setParameter("name", name);
			Profile profile = (Profile) q.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return profile;
		}catch(Exception e) {
			transaction.rollback();
			throw e;
		}
	}
	
	
	//toimii..
	@Override
	public boolean createGamedata(String name, Gamedata gamedata, Item item) {
		Transaction transaction = null;
		boolean totuus = false;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Profile profile = readProfile(name);
			Gamedata game = gamedata;
			game.setProfile(profile);
			Item uitem = item;
			game.setItem(uitem);
			uitem.setGamedata(game);
			session.saveOrUpdate(game);
			session.saveOrUpdate(uitem);
			transaction.commit();
			session.close();
			totuus = true;
		}catch(Exception e) {
			transaction.rollback();
			throw e;
		}
		return totuus;
	}
	//toimii...
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

	
	//toimii...
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gamedata> readSpecificGame(String name) {
		Transaction transaction = null;
		try(Session session = factory.openSession()){
			transaction = session.beginTransaction();
			 
			
			List<Gamedata> results = session.createQuery("select g FROM  Gamedata as g Join g.profile as p where p.name = :name").setParameter("name", name).getResultList();
			session.getTransaction().commit();
			session.close();
			return results;
		}catch(Exception e) {
			transaction.rollback();
			throw e;
			}
		
	}
	
	//toimii mut vaatii hiukan ihmettelyä vielä
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
	
	
	//muitipaikat saa mut ei tunnista get metodeja vaikka typecastaus?
	@SuppressWarnings( "unchecked")
	@Override
	
	public List<Object[]> readGamesTestaus() {
		Transaction transaction = null;
		try (Session session = factory.openSession()){
			transaction = session.beginTransaction();
			
			List<Object[]> games = session.createQuery("from Gamedata as g join g.profile as p").list();
			
			session.getTransaction().commit();
			session.close();
			return games;
			}catch(Exception e) {
			transaction.rollback();
			throw e;
			}	
	}
	
	
	

}