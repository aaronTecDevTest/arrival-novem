package com.mexxon.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 20.04.2016
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.mexxon.ImportExportMain;
import com.mexxon.utilities.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
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
    @FXML
    private AnchorPane login;

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
        login(actionEvent);
    }

  /* @FXML
    public void enterPressed(KeyEvent keyEvent){
        login.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) {
                    login(new ActionEvent());
                }
            }
        });
    }*/

    private void showMainView(ActionEvent actionEvent) {
        try {
            URL url = getClass().getResource("/fxml/FXMLArrivalMain.fxml");
            FXMLLoader loader = new FXMLLoader(url, ImportExportMain.BUNDLE_MAIN);

            Parent root = loader.load();
            Scene scene = new Scene(root,1500, 746);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            scene.getStylesheets().add("/css/arrivalMain.css");

            //primaryStage.hide();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Mexxon: ImportExportTool");
            primaryStage.setResizable(false);

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
            primaryStage.show();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void checkAuthentication(){
        if(authentication.getLoginStatus()) {
            txfUsername.setText(authentication.getUsername());
            pwfPassword.setText(authentication.getUserpassword());
            btnLogIn.setText("LogOut");
        }
    }

    private void login(ActionEvent actionEvent){
        String loginTyp = btnLogIn.getText();
        if (loginTyp.equals("LogIn")) {
            String username = txfUsername.getText();
            String password = pwfPassword.getText();

            if(username.isEmpty() && password.isEmpty()){
                lblFailLogIn.setVisible(true);
            }else {
                authentication.login(username, password);
                if (authentication.getLoginStatus()) {
                    showMainView(actionEvent);
                } else {
                    lblFailLogIn.setVisible(true);
                }
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
}