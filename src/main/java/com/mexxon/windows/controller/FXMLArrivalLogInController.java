package com.mexxon.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 20.04.2016
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import com.mexxon.utilities.SystemPreferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLArrivalLogInController implements Initializable {
    private static final Logger log = LogManager.getLogger(FXMLArrivalLogInController.class);

    @FXML
    private Button btnLogIn;
    @FXML
    private Label lblFailLogIn;
    @FXML
    private TextField txfUsername;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private CheckBox chbNoLogIn;

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
    }

    @FXML
    public void clickLogIn(ActionEvent actionEvent) {
        lblFailLogIn.setVisible(! lblFailLogIn.isVisible());

    }

    @FXML
    public void clickSignInOut(ActionEvent actionEvent) {
        log.info("Sign In");
    }
}