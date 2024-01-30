package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import dao.util.BoType;
import dto.CustomerDto;
import dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableView<CustomerTm> tbl;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn tblEmail;

    @FXML
    private TableColumn tblID;

    @FXML
    private TableColumn tblName;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblOption;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    @FXML
    void btnSaveActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtID.getText().trim().isEmpty()||
                txtName.getText().trim().isEmpty()||
                txtNumber.getText().trim().isEmpty()||
                txtEmail.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"please fill all the test fields");
            return;
        }
        CustomerDto customerDto = new CustomerDto(txtID.getText(),
                txtName.getText(),txtNumber.getText(),txtEmail.getText());
        boolean b =customerBo.saveCustomer(customerDto);
        if (b == true) {
            new Alert(Alert.AlertType.CONFIRMATION, "User added").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User already exists").show();
        }
        loadCustomerTable();
    }

    public void initialize() throws Exception{
        tblID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerTable();

        tbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
    }
    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            txtID.setEditable(false);
            txtID.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtNumber.setText(newValue.getContactNumber());
            txtEmail.setText(newValue.getEmail());
        }
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList  = customerBo.allCustomers();
            for (CustomerDto dto:dtoList) {
                Button btn = new Button("Delete");
                CustomerTm c = new CustomerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getContactNumber(),
                        dto.getEmail(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteCustomer(c.getId());
                });

                tmList.add(c);
            }
            tbl.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomer(String id) {

        try {
            boolean isDeleted = customerBo.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadCustomerTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        loadCustomerTable();
    }

    @FXML
    void btnUpdateActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException {
        customerBo.updateCustomer(new CustomerDto(txtID.getText(),txtName.getText(),
                txtNumber.getText(),txtEmail.getText()));

    }

    @FXML
    void btnBackActionPerformed(ActionEvent event) throws IOException {
        Stage stage=(Stage)pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml"))));
        stage.setResizable(false);
        stage.setTitle("DashBoard");
        stage.centerOnScreen();
    }

}
