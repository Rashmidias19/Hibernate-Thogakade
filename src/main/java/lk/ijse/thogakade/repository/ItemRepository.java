package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.config.SessionFactoryConfig;

import lk.ijse.thogakade.entity.Items;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemRepository {

    private final Session sessionItem;

    public ItemRepository(){
        sessionItem= SessionFactoryConfig.getInstance().getSession();
    }

    public int saveItem(Items items){
        Transaction transactionItem=sessionItem.beginTransaction();

        try{

            int itemId= (int) sessionItem.save(items);
            transactionItem.commit();
            sessionItem.close();

            return itemId;

        }catch (Exception e){
            transactionItem.rollback();
            sessionItem.close();
            e.printStackTrace();
            return -1;
        }

    }

    public Items getItem(int id){

        try{
            return sessionItem.get(Items.class,1);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public boolean  updateItem(Items items){
        Transaction transactionItem = sessionItem.beginTransaction();
        try {

            sessionItem.update(items);
            transactionItem.commit();
            sessionItem.close();
            return true;
        }catch (Exception e){
            transactionItem.rollback();
            sessionItem.close();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItem(Items items){
        Transaction transactionItem= sessionItem.beginTransaction();

        try{
            sessionItem.delete(items);
            transactionItem.commit();
            sessionItem.close();
            return true;
        }catch (Exception e){
            transactionItem.rollback();
            sessionItem.close();
            e.printStackTrace();
            return false;
        }
    }



}
