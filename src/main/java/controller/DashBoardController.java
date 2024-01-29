package controller;

import bo.BoFactory;
import bo.custom.UsersBo;
import dao.util.BoType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardController {
    @FXML
    public AnchorPane pane;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    private final UsersBo usersBo = BoFactory.getInstance().getBo(BoType.USERS);

    public void initialize(){

    }

    public void dateTime() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    void btnLoginOnClick(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        logIn();
    }

    @FXML
    void btnSignUpOnClick(ActionEvent event) throws  Exception {
        Stage stage= (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SignUpForm.fxml"))));
        stage.show();
    }

    private void logIn() throws SQLException, ClassNotFoundException, IOException {
        String userEmail=txtEmail.getText();
        String userPassword=txtPassword.getText();

        if ((usersBo.doesUserExist(userEmail) && usersBo.isUserCredentialsValid(userEmail, userPassword))) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Login successful").show();
            clearAllFields();

            Stage stage= (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(""))));
            stage.setResizable(false);
            stage.setTitle("Sign Up");
            stage.centerOnScreen();

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid email or password. Please try again.").show();
        }

    }

    private void clearAllFields(){
        txtEmail.clear();
        txtPassword.clear();
    }

}
