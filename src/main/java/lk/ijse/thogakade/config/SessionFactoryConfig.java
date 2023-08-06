package lk.ijse.thogakade.config;

import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {

    private final SessionFactory sessionFactory;

    private static SessionFactoryConfig factoryConfig;

    private SessionFactoryConfig(){
        sessionFactory=new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance(){
        return(null==factoryConfig)
                ?factoryConfig=new SessionFactoryConfig()
                :factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
