package com.ryhma10.tilastoohjelma.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;


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
		
	
		Profile p1 = new Profile(0,"testi", "testi");
		Profile p2 = new Profile(0, "jaa", "jaa");
		
		/*Gamedata g1 = new Gamedata(1, "wat", 6, 7, 8,
				"lose", "testi", 5, p2);
		Gamedata g2 = new Gamedata (2, "jaha", 7, 2, 3,
				"win", "mid", 10, p1);*/
		
		
		/*Item i1 = new Item("testi","testi","testi", "testi", "testi", "testi", 1, g1);
		Item i2 = new Item("jaa","jaa","jaa", "jaa", "jaa", "jaa", 2, g2);*/
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(p1);
			session.saveOrUpdate(p2);
			transaction.commit();
			
			//session.saveOrUpdate(g1);
			//session.saveOrUpdate(g2);
			//transaction.commit();
			
			/*session.saveOrUpdate(i1);
			session.saveOrUpdate(i2);
			transaction.commit();*/
			factory.close();
			
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		factory.close();
}
}
