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
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"))));
    }

    @FXML
    void btnOrderActionPerformed(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderActionPerformed(ActionEvent event) {

    }

    @FXML
    void btnReportsActionPerformed(ActionEvent event) {

    }

}
