package lk.ijse.thogakade.config;

import lk.ijse.thogakade.entity.Cart;
import lk.ijse.thogakade.entity.Cart_Order;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.entity.Items;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {

    private final SessionFactory sessionFactory;

    private static SessionFactoryConfig factoryConfig;

    private SessionFactoryConfig(){
        sessionFactory=new Configuration().configure().addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Items.class)
                .addAnnotatedClass(Cart.class)
                .addAnnotatedClass(Cart_Order.class)
                .buildSessionFactory();
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
