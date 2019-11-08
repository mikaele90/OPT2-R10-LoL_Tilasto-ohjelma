package com.ryhma10.tilastoohjelma;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

import com.ryhma10.tilastoohjelma.model.*;


public class testi {
	
	public static void main(String[] args) {
		
		ModelAccessObject modelDAO = new ModelAccessObject();
		
		SessionFactory factory = null;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try{
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Istuntotehtaan luonti ei onnistunut");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
			System.exit(-1);
		}
		
	
		Profile p1 = new Profile("testi", "testi");
		Profile p2 = new Profile("jaa", "jaa");
		
		Gamedata g1 = new Gamedata("wat", 6, 7, 8, 
				"lose", "testi", 5);
		Gamedata g2 = new Gamedata ("jaha", 7, 2, 3,
				"win", "mid", 10);
		Gamedata g3 = new Gamedata("wut", 6, 7, 8, 
				"lose", "testi", 5);
		Gamedata g4 = new Gamedata ("jaahas", 7, 2, 3,
				"win", "mid", 10);
		
		
		Item i1 = new Item("testi","testi","testi", "testi", "testi", "testi");
		Item i2 = new Item("jaa","jaa","jaa", "jaa", "jaa", "jaa");
		Item i3 = new Item("poo","pau","hau", "hau", "poo", "pp");
		Item i4 = new Item("aa","aaa","aa", "aa", "aa", "aa");
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(p1);
			session.saveOrUpdate(p2);
			transaction.commit();
			
			modelDAO.createGamedata("testi", g1, i2);
			modelDAO.createGamedata("jaa", g2, i1);
			modelDAO.createGamedata("testi", g3, i3);
			modelDAO.createGamedata("testi", g4, i4);
			
			factory.close();
			
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		factory.close();
}
}