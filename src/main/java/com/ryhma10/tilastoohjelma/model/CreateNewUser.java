package com.ryhma10.tilastoohjelma.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CreateNewUser extends Service<String> {

    private String usernameToCreate;
    private String passwordToCreate;

    public CreateNewUser(String usernameToCreate, String passwordToCreate) {
        this.usernameToCreate = usernameToCreate;
        this.passwordToCreate = passwordToCreate;
    }

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                String result = null;
                SessionFactory factory = null;
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                try{
                    factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                }catch(Exception e) {
                    StandardServiceRegistryBuilder.destroy(registry);
                    e.printStackTrace();
                    result = "Fail";
                    System.exit(-1);
                }
                Profile newProfile = new Profile(usernameToCreate, passwordToCreate);
                Transaction transaction = null;
                try (Session session = factory.openSession()) {
                    transaction = session.beginTransaction();

                    session.saveOrUpdate(newProfile);
                    transaction.commit();
                    factory.close();
                    result = "Success";
                }
                catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    factory.close();
                    result = "Fail";
                }
                if (!factory.isClosed()) {
                    factory.close();
                }
                Thread.sleep(50);
                return result;
            }
        };
    }
}
