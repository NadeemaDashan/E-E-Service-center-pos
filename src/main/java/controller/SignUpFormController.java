package controller;

import bo.BoFactory;
import bo.custom.UsersBo;
import dao.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {

    public AnchorPane pane;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtVerifyPassword;

    private UsersBo usersBo = BoFactory.getInstance().getBo(BoType.USERS);

    @FXML
    void btnBackActionPerformed(ActionEvent event) throws IOException {
    changeView();
    }

    @FXML
    void btnSignUpActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String email=txtEmail.getText();
        String password=txtPassword.getText();
        String verifyPassword=txtVerifyPassword.getText();

        if (email.isEmpty()||password.isEmpty()||verifyPassword.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields").show();
            clearFields();
            return;
        }
        if (!password.equals(verifyPassword)){
            new Alert(Alert.AlertType.ERROR,"Password mismatch").show();
            clearFields();
            return;
        }

        if (password.length()<9){
            new Alert(Alert.AlertType.ERROR,"Password length should be greater than 9").show();
            clearFields();
            return;
        }
        boolean b = usersBo.saveUser(email, password);
        if (b == false) {
            new Alert(Alert.AlertType.ERROR, "User already exist").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "User saved").show();
            changeView();
        }


    }
    void changeView() throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
        stage.setResizable(false);
        stage.setTitle("Log In");
        stage.show();
    }
    public void clearFields(){
        txtEmail.clear();
        txtPassword.clear();
        txtVerifyPassword.clear();
    }

}
