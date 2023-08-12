package lk.ijse.thogakade.controller;

/*
    @author DanujaV
    @created 3/14/23 - 9:44 AM
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakade.entity.Items;

import lk.ijse.thogakade.repository.ItemRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/Thogakade_Hibernate";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

    }
//
//    private void setCellValueFactory() {
//        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        colQty.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
//        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
//    }
//
//    private void getAll() {
//        try {
//            ObservableList<ItemTM> obList = FXCollections.observableArrayList();
//            List<Item> cusList = ItemModel.getAll();
//
//            for (Item item : cusList) {
//                obList.add(new ItemTM(
//                        item.getCode(),
//                        item.getDescription(),
//                        item.getUnitPrice(),
//                        item.getQtyOnHand()
//                ));
//            }
//            tblItem.setItems(obList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
//        }
//    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int itemId= Integer.parseInt(txtCode.getText());
        ItemRepository itemRepository=new ItemRepository();
        Items existingItems =itemRepository.getItem(itemId);

        boolean isDeleted=itemRepository.deleteItem(existingItems);
        if(isDeleted){
            System.out.println("Item Deleted");
        }else{
            System.out.println("not deleted");
        }
    }

    @FXML
    void btnGetAllOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        Items items =getItem();
        ItemRepository itemRepository=new ItemRepository();

        int savedItemId=itemRepository.saveItem(items);
        System.out.println("Saved item id:" + savedItemId);
    }

    private Items getItem() {
        Items items =new Items();
        items.setDescription(txtDescription.getText());
        items.setUnitPrice(Double.valueOf(txtUnitPrice.getText()));
        items.setQtyOnHand(Integer.valueOf(txtQtyOnHand.getText()));
        return items;
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ItemRepository itemRepository=new ItemRepository();
        Items items =findItem();
        boolean isUpdated=itemRepository.updateItem(items);

        if(isUpdated){
            System.out.println("Item updated");
        }else{
            System.out.println("not updated");
        }
    }

    private Items findItem() {
        Items items =new Items();
        items.setCode(Integer.parseInt(txtCode.getText()));
        items.setDescription(txtDescription.getText());
        items.setUnitPrice(Double.valueOf(txtUnitPrice.getText()));
        items.setQtyOnHand(Integer.valueOf(txtQtyOnHand.getText()));
        items.setCreatedDateTime(Timestamp.valueOf(LocalDateTime.now()));
        return items;
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) {

    }
}
