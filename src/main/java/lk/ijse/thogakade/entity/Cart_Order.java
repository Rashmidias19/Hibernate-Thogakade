package lk.ijse.thogakade.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cart_order")
public class Cart_Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerOrder_code")
    private int customerOrder_id;

    @Column(name = "order_code")
    private int code;

    @Column(name = "customer_id")
    private int customer_id;

    @CreationTimestamp
    private Timestamp createdDateTime;

    public Cart_Order() {
    }

    public Cart_Order(int customerOrder_id, int code, int customer_id, Timestamp createdDateTime) {
        this.customerOrder_id = customerOrder_id;
        this.code = code;
        this.customer_id = customer_id;
        this.createdDateTime = createdDateTime;
    }

    public int getCustomerOrder_id() {
        return customerOrder_id;
    }

    public void setCustomerOrder_id(int customerOrder_id) {
        this.customerOrder_id = customerOrder_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "Cart_Order{" +
                "customerOrder_id=" + customerOrder_id +
                ", code=" + code +
                ", customer_id=" + customer_id +
                ", createdDateTime=" + createdDateTime +
                '}';
    }
}
