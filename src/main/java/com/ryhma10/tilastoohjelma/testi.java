package com.ryhma10.tilastoohjelma;

import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

import com.ryhma10.tilastoohjelma.model.Gamedata;


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
		
	
		SoftwareProfile p1 = new SoftwareProfile("testi", "testi", null, null, null, null);
		SoftwareProfile p2 = new SoftwareProfile("jaa", "jaa", null, null, null, null);
		
		Gamedata g1 = new Gamedata("wat", 6, 7, 8, 
				"lose", "testi", 5, "kama", "kama", "kama", "kama","kama","kama","jaa");
		Gamedata g2 = new Gamedata ("jaha", 7, 2, 3,
				"win", "mid", 10, "kama","kama","kama","kama","kama","kama", "testi");
		Gamedata g3 = new Gamedata("wut", 6, 7, 8, 
				"lose", "testi", 5, "kama","kama","kama","kama","kama","kama", "jaa");
		Gamedata g4 = new Gamedata ("jaahas", 7, 2, 3,
				"win", "mid", 10, "kama","kama","kama","kama","kama","kama","jaa");
		
		
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
			
			session.saveOrUpdate(g3);
			session.saveOrUpdate(g4);
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
