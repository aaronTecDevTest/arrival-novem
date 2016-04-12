package com.mexxon.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 20.04.2016
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.mexxon.utilities.Authentication;
import com.mexxon.utilities.SystemPreferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLArrivalLogInController implements Initializable {
    private static final Logger log = LogManager.getLogger(FXMLArrivalLogInController.class);
    private Authentication authentication = Authentication.getInstance();

    @FXML
    private Button btnLogIn;
    @FXML
    private Label lblFailLogIn;
    @FXML
    private TextField txfUsername;
    @FXML
    private PasswordField pwfPassword;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkAuthentication();
    }

    @FXML
    public void clickLogIn(ActionEvent actionEvent) {

        String loginTyp = btnLogIn.getText();
        if (loginTyp.equals("LogIn")) {
            String username = txfUsername.getText();
            String password = pwfPassword.getText();
            if(username.isEmpty())
                username ="";

            if( password.isEmpty())
                password="";

            authentication.login(username, password);
            if (authentication.getLoginStatus()) {
                showMainView(actionEvent);
            } else {
                lblFailLogIn.setVisible(lblFailLogIn.isVisible());
            }
        }

        if (loginTyp.equals("LogOut")) {
            authentication.logout();
            if(!authentication.getLoginStatus()){
                txfUsername.setText("");
                pwfPassword.setText("");
                btnLogIn.setText("LogIn");
            }
        }
    }

    private void showMainView(ActionEvent actionEvent) {
        try {
            URL url = getClass().getResource("/fxml/FXMLArrivalMain.fxml");
            FXMLLoader loader = new FXMLLoader(url, SystemPreferences.getResourceBundle("arrivalMain"));

            Parent root = loader.load();
            Scene scene = new Scene(root);//,1300, 530);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            scene.getStylesheets().add("/css/arrivalMain.css");

            //primaryStage.hide();
            primaryStage.setScene(scene);
            primaryStage.setTitle("");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }

    private void checkAuthentication(){
        if(authentication.getLoginStatus()) {
            txfUsername.setText(authentication.getUsername());
            pwfPassword.setText(authentication.getUserpassword());
            btnLogIn.setText("LogOut");
        }
    }

}