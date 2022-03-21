package com.example.roskilde_daycare;

import com.example.roskilde_daycare.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController extends CustomStage {

    @FXML
    private TextField userEmail;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Button loginBtn;

    // must be public
    @FXML
    public void initialize(){
        loginBtn.setDisable(true);
    }

    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {

        DB_Connector.login(event, userEmail.getText(), userPassword.getText());

       /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pupils.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML
    protected void handleKeyReleased() {
        String emailText = userEmail.getText();
        String passwordText = userPassword.getText();
        boolean disableButton = emailText.isEmpty() || emailText.trim().isEmpty() || passwordText.isEmpty() || passwordText.trim().isEmpty();
        loginBtn.setDisable(disableButton);
    }


}
