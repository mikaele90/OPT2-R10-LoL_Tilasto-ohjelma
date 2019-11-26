package com.ryhma10.tilastoohjelma;

import com.ryhma10.tilastoohjelma.model.SoftwareProfile;
import com.ryhma10.tilastoohjelma.model.Team;
import com.ryhma10.tilastoohjelma.model.Additional;
import com.ryhma10.tilastoohjelma.model.Gamedata;
import com.ryhma10.tilastoohjelma.model.Item;
import com.ryhma10.tilastoohjelma.model.ModelAccessObject;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;


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
		
	
		SoftwareProfile p1 = new SoftwareProfile("testi", "testi", "testi", "testi", "testi", "testi");
		SoftwareProfile p2 = new SoftwareProfile("jaa", "jaa", "jaa", "jaa", "jaa", "jaa");

		Gamedata g1 = new Gamedata(55, "jaska","wat", 6, 7, 8, 
				"lose", "testi", "bronze");
		Gamedata g2 = new Gamedata (60, "sebu","jaha", 7, 2, 3,
				"win", "mid", "bronze");
		Gamedata g3 = new Gamedata(45, "jaska", "wut", 6, 7, 8, 
				"lose", "testi", "bronze");
		Gamedata g4 = new Gamedata (40, "jaska", "jaahas", 7, 2, 3,
				"win", "mid", "bronze");
		
		
		Item i1 = new Item("testi","testi","testi", "testi", "testi", "testi", "testi");
		Item i2 = new Item("jaa","jaa","jaa", "jaa", "jaa", "jaa", "aaaa");
		Item i3 = new Item("poo","pau","hau", "hau", "poo", "pp", "aaaa");
		Item i4 = new Item("aa","aaa","aa", "aa", "aa", "aa","aaaa");
		
		Team t1 = new Team("hero", "hero", "hero", "hero", "hero", "hero", "hero", "hero", "hero");
		Team t2 = new Team("champion", "champion", "champion", "champion", "champion","champion", "champion", "champion", "champion");
		Team t3 = new Team("sankari", "sankari", "sankari", "sankari", "sankari", "sankari", "sankari", "sankari", "sankari");
		Team t4 = new Team("mato", "mato", "mato", "mato", "mato", "mato", "mato", "mato", "mato");
		
		Additional a1 = new Additional(475, 500, "normi", 4, 7, "joulukuu", 80, 25);
		Additional a2 = new Additional(425, 400, "normi", 4, 7, "tammi", 7, 3);
		Additional a3 = new Additional(488, 552, "normi", 4, 7, "maalis", 4, 9);
		Additional a4 = new Additional(4, 5, "normi", 4, 7, "kesä",5 ,6);
		

		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(p1);
			session.saveOrUpdate(p2);
			transaction.commit();
			

			modelDAO.createGamedata("testi", g1, i2, t1, a1);
			modelDAO.createGamedata("jaa", g2, i1, t2 ,a2);
			modelDAO.createGamedata("testi", g3, i3, t3, a3);
			modelDAO.createGamedata("testi", g4, i4, t4, a4);
			
			factory.close();
			
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		factory.close();
	}
}