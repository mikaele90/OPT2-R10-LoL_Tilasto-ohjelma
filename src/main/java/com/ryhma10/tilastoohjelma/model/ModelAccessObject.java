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
	
	@Override
	public boolean createGamedata(Gamedata gamedata) {
		Transaction transaction = null;
		boolean totuus = false;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Gamedata game = new Gamedata(gamedata.getChampion(), gamedata.getKills(), gamedata.getDeaths(),
					gamedata.getAssits(), gamedata.getPositio(), gamedata.getWinlose(), gamedata.getGpm(),
					gamedata.getSlot1(), gamedata.getSlot2(), gamedata.getSlot3(), gamedata.getSlot4(),
					gamedata.getSlot5(), gamedata.getSlot6(), gamedata.getPname());
			session.saveOrUpdate(game);
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
	public List<Gamedata> readSpesificGames(String pname) {
		Transaction transaction = null;
		try (Session session = factory.openSession()){
			transaction = session.beginTransaction();
			List<Gamedata> games = session.createQuery("FROM Gamedata WHERE pname = :pname ORDER BY gameid DESC").setParameter("pname", pname).getResultList();
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

}	