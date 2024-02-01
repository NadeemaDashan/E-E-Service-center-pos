package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane pane;

    @FXML
    void btnCustomerActionPerformed(ActionEvent event) throws IOException {
        Stage stage=(Stage)pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
        stage.setTitle("Customer Form");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnItemsActionPerformed(ActionEvent event) throws IOException {
        Stage stage=(Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"))));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Items");
        stage.show();
    }

    @FXML
    void btnOrderActionPerformed(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderActionPerformed(ActionEvent event) throws IOException {
        Stage stage=(Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrderForm.fxml"))));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Place Order");
        stage.show();
    }

    @FXML
    void btnReportsActionPerformed(ActionEvent event) {

    }

}
