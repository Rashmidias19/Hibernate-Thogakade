package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.config.SessionFactoryConfig;
import lk.ijse.thogakade.entity.Cart_Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CartOrderRepository {
    private final Session session;

    public CartOrderRepository(){
        session= SessionFactoryConfig.getInstance().getSession();
    }

    public int saveCartOrder(Cart_Order cartOrder){

        Session session = null;
        Transaction transaction = null;
        int isSaved = 0;
        try {
            session = SessionFactoryConfig.getInstance().getSession();
            transaction = session.beginTransaction();

            isSaved = (int) session.save(cartOrder);
            transaction.commit();
                System.out.println("Saved ID:"+isSaved);


        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
           session.close();
        }

        return isSaved;
    }
}
