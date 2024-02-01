package controller;
import bo.BoFactory;
import bo.custom.ItemBo;
import dao.util.BoType;
import dto.ItemDto;
import dto.tm.ItemTm;
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

public class ItemFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private ChoiceBox<String> txtCatogery;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtProductName;

    @FXML
    private ChoiceBox<String> txtStatus;

    @FXML
    private TableColumn colCatogery;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TableColumn colContact;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colOption;

    @FXML
    private TableColumn colProduct;

    @FXML
    private TableColumn colStatus;
    private static int itemCounter = 0;

    private final ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    public void initialize() throws SQLException, ClassNotFoundException {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCatogery.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        loadItems();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
        comboBoxSetItems();
        generateId();

    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {
            txtItemCode.setEditable(true);
            txtItemCode.setText(newValue.getCode());
            txtProductName.setText(newValue.getName());
            txtCatogery.setValue(newValue.getCategory());
            txtStatus.setValue(newValue.getStatus());
            txtContact.setText(newValue.getContact());
        }
    }

    private void loadItems() throws SQLException, ClassNotFoundException {
        List<ItemDto> all = itemBo.getAll();
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        for (ItemDto item : all) {
            Button btn = new Button("Delete");
            ItemTm itemTm = new ItemTm
                    (item.getCode(),
                            item.getName()
                            , item.getCategory(),
                            item.getStatus(),
                            btn,
                            item.getContact());
            btn.setOnAction(actionEvent -> {
                try {
                    deleteItem(item.getCode());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            tmList.add(itemTm);
        }
        tblItem.setItems(tmList);
    }

    void deleteItem(String code) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = itemBo.deleteItem(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                loadItems();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        loadItems();
    }

    @FXML
    void btnBackActionPerformed(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("DashBoard");
        stage.setResizable(false);
        stage.show();
    }

    void comboBoxSetItems() {
        ObservableList<String> categoryOptions = FXCollections.observableArrayList("Electric", "Electronic");
        txtCatogery.setItems(categoryOptions);
        ObservableList<String> txtStatus = FXCollections.observableArrayList("Pending", "Processing", "Completed");
        this.txtStatus.setItems(txtStatus);
    }

    @FXML
    void btnSaveActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException {
        itemBo.saveItem(new ItemDto(txtItemCode.getText(),
                txtProductName.getText(),
                txtCatogery.getValue(),
                txtStatus.getValue(),
                txtContact.getText()));
        loadItems();
    }

    void generateId() {
        String prefix = "I#";
        String formattedCounter = String.format("%05d", itemCounter + 1);
        String generatedCode = prefix + formattedCounter;
        txtItemCode.setText(generatedCode);
    }

    @FXML
    void btnUpdateActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException {
        itemBo.updateItem(new ItemDto (txtItemCode.getText(),
                txtProductName.getText(),
                txtCatogery.getValue(),
                txtStatus.getValue(),
                txtContact.getText()));
        new Alert(Alert.AlertType.CONFIRMATION,"Item updated !");
        loadItems();
    }
}
