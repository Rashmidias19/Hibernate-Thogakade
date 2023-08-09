package lk.ijse.thogakade.controller;

/*
    @author DanujaV
    @created 3/21/23 - 10:25 AM
*/

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.thogakade.entity.Cart;
import lk.ijse.thogakade.entity.Cart_Order;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.entity.Items;
import lk.ijse.thogakade.repository.CartOrderRepository;
import lk.ijse.thogakade.repository.CustomerRepository;
import lk.ijse.thogakade.repository.ItemRepository;
import lk.ijse.thogakade.repository.OrderRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    public TextField txtCustId;
    public TextField txtItemId;
    public TextField txtDescription;
    public Label lblTotal;
    public Label lblPrice;
    public TextField txtOrderID;


    @FXML
    private AnchorPane pane;


    @FXML
    private TextField txtQty;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        ItemRepository itemRepository=new ItemRepository();
        Items item=itemRepository.getItem(Integer.parseInt(txtItemId.getText()));
        fillItemFields(item);
        calculateNetTotal();
    }

    private void calculateNetTotal() {

//        double total= Double.parseDouble(String.valueOf(Double.parseDouble(lblPrice.getText())*Integer.parseInt(txtQty.getText())));
//        lblTotal.setText(String.valueOf(total));
        int quantity= Integer.parseInt(txtQty.getText());
        double price= Double.parseDouble(lblPrice.getText());
        double total=price*quantity;
        lblTotal.setText(String.valueOf(total));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");

        stage.centerOnScreen();
    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {


        Cart cart=getCart();
        OrderRepository orderRepository=new OrderRepository();

        int savedOrderId=orderRepository.saveOrder(cart);
        System.out.println("Saved order id:" + savedOrderId);

        Cart_Order cartOrder=getCartOrder(savedOrderId);
        CartOrderRepository cartOrderRepository=new CartOrderRepository();

        int savedCartID=cartOrderRepository.saveCartOrder(cartOrder);
    }

    private Cart_Order getCartOrder(int savedOrderId) {
        Cart_Order cartOrder=new Cart_Order();
        Cart cart=new Cart();
        cartOrder.setCode(savedOrderId);
        cartOrder.setCustomer_id(Integer.parseInt(txtCustId.getText()));
        return cartOrder;
    }

    private Cart getCart() {
        Cart cart=new Cart();
        cart.setCustomer_id(Integer.parseInt(txtCustId.getText()));
        cart.setItemId(Integer.parseInt(txtItemId.getText()));
        cart.setDescription(txtDescription.getText());
        cart.setQty(Integer.valueOf(txtQty.getText()));
        cart.setUnitPrice(Double.valueOf(lblPrice.getText()));
        cart.setTotal(Double.valueOf(lblTotal.getText()));

        return cart;
    }


    private void fillItemFields(Items item) {
        lblPrice.setText(String.valueOf(item.getUnitPrice()));

    }


    public void txtQtyOnAction(ActionEvent event) {
    }

    public void btnDeleteOnAction(ActionEvent event) {
        int orderId= Integer.parseInt(txtOrderID.getText());
        OrderRepository orderRepository=new OrderRepository();
        Cart existingOrder=orderRepository.getOrder(orderId);

        boolean isDeleted=orderRepository.deleteOrder(existingOrder);
        if(isDeleted){
            System.out.println("Order Deleted");
        }else{
            System.out.println("not deleted");
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        OrderRepository orderRepository=new OrderRepository();
        Cart cart=findCart();
        boolean isUpdated=orderRepository.updateOrder(cart);

        if(isUpdated){
            System.out.println("Order updated");
        }else{
            System.out.println("not updated");
        }
    }

    private Cart findCart() {
        Cart cart=new Cart();
       cart.setCode(Integer.parseInt(txtOrderID.getText()));
       cart.setCustomer_id(Integer.parseInt(txtCustId.getText()));
       cart.setItemId(Integer.parseInt(txtItemId.getText()));
       cart.setDescription(txtDescription.getText());
       cart.setQty(Integer.valueOf(txtQty.getText()));
       cart.setTotal(Double.valueOf(lblTotal.getText()));
       cart.setUnitPrice(Double.valueOf(lblPrice.getText()));

        return cart;
    }
}
