package lk.ijse.thogakade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.repository.CustomerRepository;
//import lk.ijse.thogakade.entity.Customer;
//import lk.ijse.thogakade.dto.tm.CustomerTM;
//import lk.ijse.thogakade.model.CustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/Thogakade_Hibernate";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
//
//    Customer customer=getCustomer();
//    CustomerRepository customerRepository=new CustomerRepository();


    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;


//    @Override
//    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
//        setCellValueFactory();
//        getAll();
//    }
//
//    private void setCellValueFactory() {
//        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
//    }
//
//    private void getAll() {
//        try {
//            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
//            List<Customer> cusList = CustomerModel.getAll();
//
//            for (Customer customer : cusList) {
//                obList.add(new CustomerTM(
//                        customer.getId(),
//                        customer.getName(),
//                        customer.getAddress(),
//                        customer.getSalary()
//                ));
//            }
//            tblCustomer.setItems(obList);
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

//    @FXML
//    void btnClearOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void btnDeleteOnAction(ActionEvent event) throws SQLException {
//        String id = txtId.getText();
//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "DELETE FROM Customer WHERE id = ?";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, id);
//
//            if (pstm.executeUpdate() > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "deletd!").show();
//            }
//        }
//    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        Customer customer=getCustomer();
        CustomerRepository customerRepository=new CustomerRepository();

         int savedCusId=customerRepository.saveCustomer(customer);
        System.out.println("Saved customer id:" + savedCusId);
    }

    private Customer getCustomer() {
        Customer customer=new Customer();
        customer.setName(txtName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setSalary(Double.valueOf(txtSalary.getText()));

        return customer;
    }

    public void codeSearchOnAction(ActionEvent event) {
    }

    public void btnDeleteOnAction(ActionEvent event) {
         int customerId= Integer.parseInt(txtId.getText());
        CustomerRepository customerRepository=new CustomerRepository();
        Customer existingCustomer=customerRepository.getCustomer(customerId);

        boolean isDeleted=customerRepository.deleteCustomer(existingCustomer);
        if(isDeleted){
            System.out.println("Customer Deleted");
        }else{
            System.out.println("not deleted");
        }

    }

    public void btnUpdateOnAction(ActionEvent event) {
        CustomerRepository customerRepository=new CustomerRepository();
        Customer customer=findCustomer();
        boolean isUpdated=customerRepository.updateCustomer(customer);

        if(isUpdated){
            System.out.println("Customer updated");
        }else{
            System.out.println("not updated");
        }
    }

    private Customer findCustomer() {
        Customer customer=new Customer();
        customer.setId(Integer.parseInt(txtId.getText()));
        customer.setName(txtName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setSalary(Double.valueOf(txtSalary.getText()));
        customer.setCreatedDateTime(Timestamp.valueOf(LocalDateTime.now()));
        return customer;
    }

    public void btnClearOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

    }

//    @FXML
//    void btnUpdateOnAction(ActionEvent event) throws SQLException {
//        String id = txtId.getText();
//        String name = txtName.getText();
//        String address = txtAddress.getText();
//        double salary = Double.parseDouble(txtSalary.getText());
//
//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ?";
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, name);
//            pstm.setString(2, address);
//            pstm.setDouble(3, salary);
//            pstm.setString(4, id);
//
//            boolean isUpdated = pstm.executeUpdate() > 0;
//            if (isUpdated) {
//                new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
//            }
//        }
//    }

//    @FXML
//    void codeSearchOnAction(ActionEvent event) throws SQLException {
//        String id = txtId.getText();
//
//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "SELECT * FROM Customer WHERE id = ?";
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, id);
//
//            ResultSet resultSet = pstm.executeQuery();
//            if (resultSet.next()) {
//                String cus_id = resultSet.getString(1);
//                String cus_name = resultSet.getString(2);
//                String cus_address = resultSet.getString(3);
//                double cus_salary = resultSet.getDouble(4);
//
//                txtId.setText(cus_id);
//                txtName.setText(cus_name);
//                txtAddress.setText(cus_address);
//                txtSalary.setText(String.valueOf(cus_salary));
//            }
//        }
//    }

}
