package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.OrderBo;
import bo.custom.UsersBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.UsersBoImpl;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dao.util.BoType;
import dto.CustomerDto;
import dto.ItemDto;
import dto.tm.CustomerTm;
import dto.tm.ItemTm;
import dto.tm.OrderTm;
import entity.Customer;
import entity.Item;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderFormController {

    @FXML
    private ComboBox<?> cmbCustomerCode;

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colFault;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colOption;

    @FXML
    private TableColumn colPrice;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblOrderId;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<OrderTm> tblOrders;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtFault;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;

    private List<CustomerDto> customers;
    private List<Item> items;
    private ItemDao itemDao = new ItemDaoImpl();

    private final CustomerBo customerBo = new CustomerBoImpl();
    ObservableList<OrderTm> tmList = FXCollections.observableArrayList();
    private int total;
    public OrderFormController() throws SQLException, ClassNotFoundException {
    }


    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("product"));
        colFault.setCellValueFactory(new PropertyValueFactory<>("fault"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        try {
            customers = customerBo.allCustomers();
            items = itemDao.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        loadCustomerContact();
        loadItemCodes();

        cmbCustomerCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CustomerDto dto:customers) {
                if (dto.getId().equals(newValue.toString())){
                    txtContactNumber.setText(dto.getContactNumber());
                    txtCusName.setText(dto.getName());
                }
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (Item dto:items) {
                if (dto.getCode().equals(newValue.toString())){
                    txtProductName.setText(dto.getName());
                }
            }
        });
    }

    private void loadCustomerContact() {
        ObservableList list = FXCollections.observableArrayList();


        for (CustomerDto dto:customers) {
            list.add(dto.getId());
        }

        cmbCustomerCode.setItems(list);
    }
    private void loadItemCodes() {
        ObservableList list = FXCollections.observableArrayList();

        for (Item item:items) {
            list.add(item.getCode());
        }

        cmbItemCode.setItems(list);
    }

    @FXML
    void btnBackActionPerformed(ActionEvent event) throws IOException {
        Stage stage=(Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml"))));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("DashBoard");
        stage.show();
    }

    @FXML
    void btnPlaceOrderActionPerformed(ActionEvent event) {

    }
    @FXML
    void btnAddToCartActionPerformed(ActionEvent event) {
    Button btn = new Button("Delete");
    OrderTm orderTm = new OrderTm(cmbItemCode.getValue().toString(),
            txtProductName.getText(),
            txtFault.getText(),
            txtPrice.getText(),
            btn);
    tmList.add(orderTm);
    tblOrders.setItems(tmList);
    btn.setOnAction(actionEvent -> {
        tmList.remove(orderTm);
        total=total-Integer.parseInt(orderTm.getPrice());
        lblAmount.setText(String.valueOf(total));
    });
    total+=Integer.parseInt(txtPrice.getText());
    lblAmount.setText(String.valueOf(total));
    }
}
