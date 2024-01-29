package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpFormController {

    public AnchorPane pane;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtVerifyPassword;

    @FXML
    void btnBackActionPerformed(ActionEvent event) throws IOException {
    Stage stage = (Stage) pane.getScene().getWindow();
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    stage.setResizable(false);
    stage.setTitle("Log In");
    stage.show();
    }

    @FXML
    void btnSignUpActionPerformed(ActionEvent event) {

    }

}
