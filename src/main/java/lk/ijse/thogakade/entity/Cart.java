package lk.ijse.thogakade.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "cart")
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_code")
    private int code;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "quantity")
    private Integer qty;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "total")
    private Double total;

    @CreationTimestamp
    private Timestamp createdDateTime;

    public Cart(int code,int customer_id, int itemId, Integer qty, Double unitPrice, String description, Double total, Timestamp createdDateTime) {
        this.code = code;
        this.customer_id = customer_id;
        this.itemId = itemId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.description = description;
        this.total = total;
        this.createdDateTime = createdDateTime;

    }

    public Cart() {
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getQty(String text) {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotal(double v) {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "code=" + code +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", description='" + description + '\'' +
                ", total=" + total +
                ", createdDateTime=" + createdDateTime +
                '}';
    }

    public void getDescription(String text) {
    }
}
