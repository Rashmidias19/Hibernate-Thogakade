//package lk.ijse.thogakade.controller;
//
///*
//    @author DanujaV
//    @created 3/14/23 - 9:44 AM
//*/
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import lk.ijse.thogakade.entity.Item;
//import lk.ijse.thogakade.dto.tm.ItemTM;
//import lk.ijse.thogakade.model.ItemModel;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Properties;
//import java.util.ResourceBundle;
//
//public class ItemFormController implements Initializable {
//    private static final String URL = "jdbc:mysql://localhost:3306/Thogakade_Hibernate";
//    private static final Properties props = new Properties();
//
//    static {
//        props.setProperty("user", "root");
//        props.setProperty("password", "1234");
//    }
//    @FXML
//    private TableColumn<?, ?> colAction;
//
//    @FXML
//    private TableColumn<?, ?> colCode;
//
//    @FXML
//    private TableColumn<?, ?> colDescription;
//
//    @FXML
//    private TableColumn<?, ?> colQty;
//
//    @FXML
//    private TableColumn<?, ?> colQtyOnHand;
//    @FXML
//    private AnchorPane root;
//    @FXML
//    private TextField txtCode;
//
//    @FXML
//    private TextField txtDescription;
//
//    @FXML
//    private TextField txtQtyOnHand;
//
//    @FXML
//    private TextField txtUnitPrice;
//
//    public TableView<ItemTM> tblItem;
//
//    @Override
//    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
//        setCellValueFactory();
//        getAll();
//    }
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
//
//
//    @FXML
//    void btnBackOnAction(ActionEvent event) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
//
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setTitle("Dashboard");
//        stage.setScene(new Scene(parent));
//        stage.centerOnScreen();
//    }
//
//    @FXML
//    void btnDeleteOnAction(ActionEvent event) {
//        String code = txtCode.getText();
//        try {
//            boolean isDeleted = ItemModel.delete(code);
//            if (isDeleted) {
//                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
//        }
//    }
//
//    @FXML
//    void btnGetAllOnAction(ActionEvent event) {
//        try {
//            List<Item> itemList = ItemModel.getAll();
//
//            if (itemList != null) {
//                for (Item item : itemList) {
//                    System.out.println(item.getCode() + " - " + item.getDescription() + " - " + item.getUnitPrice() + " - " + item.getQtyOnHand());
//                }
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "something happend!").show();
//        }
//    }
//
//    @FXML
//    void btnSaveOnAction(ActionEvent event) {
//        String code = txtCode.getText();
//        String description = txtDescription.getText();
//        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
//        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
//
//        Item item = new Item(code, description, unitPrice, qtyOnHand);
//
//        try {
////            boolean isSaved = ItemModel.save(code, description, unitPrice, qtyOnHand);
//            boolean isSaved = ItemModel.save(item);
//            if (isSaved) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
//        }
//    }
//
//    @FXML
//    void btnUpdateOnAction(ActionEvent event) {
//        String code = txtCode.getText();
//        String description = txtDescription.getText();
//        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
//        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
//
//        try {
//            boolean isUpdated = ItemModel.update(code, description, unitPrice, qtyOnHand);
//            new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
//        }
//    }
//
//    @FXML
//    void codeSearchOnAction(ActionEvent event) {
//        try {
//            Item item = ItemModel.search(txtCode.getText());
//            if (item != null) {
//                txtCode.setText(item.getCode());
//                txtDescription.setText(item.getDescription());
//                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
//                txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "something happened!").show();
//        }
//    }
//}
