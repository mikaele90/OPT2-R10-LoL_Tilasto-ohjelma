package com.ryhma10.tilastoohjelma;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

import com.ryhma10.tilastoohjelma.model.Gamedata;
import com.ryhma10.tilastoohjelma.model.Item;
import com.ryhma10.tilastoohjelma.model.Profile;


public class testi {
	
	public static void main(String[] args) {
		
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
				"lose", "testi", 5, p2);
		Gamedata g2 = new Gamedata ("jaha", 7, 2, 3,
				"win", "mid", 10, p1);
		Gamedata g3 = new Gamedata("wut", 6, 7, 8, 
				"lose", "testi", 5, p1);
		Gamedata g4 = new Gamedata ("jaahas", 7, 2, 3,
				"win", "mid", 10, p1);
		
		
		Item i1 = new Item("testi","testi","testi", "testi", "testi", "testi", g1);
		Item i2 = new Item("jaa","jaa","jaa", "jaa", "jaa", "jaa", g2);
		Item i3 = new Item("poo","pau","hau", "hau", "poo", "pp", g3);
		Item i4 = new Item("aa","aaa","aa", "aa", "aa", "aa", g4);
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(p1);
			session.saveOrUpdate(p2);
			transaction.commit();
			
			transaction = null;
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(g1);
			session.saveOrUpdate(g2);
			transaction.commit();
			
			transaction = null;
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(i1);
			session.saveOrUpdate(i2);
			transaction.commit();
			
			transaction = null;
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(g3);
			session.saveOrUpdate(g4);
			transaction.commit();
			
			transaction = null;
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(i3);
			session.saveOrUpdate(i4);
			transaction.commit();
			
			
			
			
			factory.close();
			
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		factory.close();
}
}