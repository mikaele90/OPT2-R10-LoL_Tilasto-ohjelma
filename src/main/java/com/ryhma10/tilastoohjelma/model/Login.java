package com.ryhma10.tilastoohjelma.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Login extends Service<Profile> {

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    private String profileName, profilePassword;

    public Login(String profileName, String profilePassword) {
        this.profileName = profileName;
        this.profilePassword = profilePassword;
    }

    @Override
    protected Task<Profile> createTask() {
        return new Task<Profile>() {
            @Override
            protected Profile call() throws Exception {
                Profile result = null;
                SessionFactory factory = null;
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                try{
                    factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                }catch(Exception e) {
                    StandardServiceRegistryBuilder.destroy(registry);
                    e.printStackTrace();
                    return result;
                }
                try (Session session = factory.openSession()) {
                    Query query = session.createQuery("FROM Profile WHERE name = :name AND Psw = :password");
                    query.setParameter("name", profileName);
                    query.setParameter("password", profilePassword);

                    List queryResults = query.list();
                    if(queryResults.size() == 1) {
                        Profile profile = (Profile)queryResults.get(0);
                        System.out.println(profile.getName());
                        result = profile;
                    }
                    else if (queryResults.size() > 1) {
                        System.out.println("Too many records found! Clean up database.");
                    }
                }
                catch (Exception e) {
                    factory.close();
                }
                if (!factory.isClosed()) {
                    factory.close();
                }
                Thread.sleep(50);
                return result;
            }
        };
    }

    public String getProfileName() {
        return profileName;
    }

}
