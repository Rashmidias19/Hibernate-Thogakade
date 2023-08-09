package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.config.SessionFactoryConfig;
import lk.ijse.thogakade.entity.Cart;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.Order;

public class OrderRepository {

    private final Session session;

    public OrderRepository(){
        session= SessionFactoryConfig.getInstance().getSession();
    }

    public int saveOrder(Cart cart){
        Transaction transaction=session.beginTransaction();

        try{

            int orderId= (int) session.save(cart);
            transaction.commit();
            session.close();

            return orderId;

        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1;
        }

    }

    public Cart getOrder(int id){

        try{
            return session.get(Cart.class,id);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public boolean  updateOrder(Cart cart){
        Transaction transaction = session.beginTransaction();
        try {

            session.update(cart);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(Cart cart){
        Transaction transaction= session.beginTransaction();

        try{
            session.delete(cart);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

}
