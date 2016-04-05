package com.mexxon.windows.controller;

import com.mexxon.utilities.SystemPreferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016.
 * @since: 1.0
 * Package: com.mexxon.windows.controller
 */
public class FXMLArrivalMainController implements Initializable {
    private static final Logger log = LogManager.getLogger(FXMLArrivalMainController.class);

    @FXML
    private Button btnAllStates;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnLogInOut;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnRun;
    @FXML
    private Button btnStop;

    /**
     * @param bundle For Internationalization
     */
    private ResourceBundle bundle;

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
         bundle = resources;
    }

    @FXML
    public void showLogInOut(ActionEvent actionEvent) {
        try {

            URL url = getClass().getResource("/fxml/FXMLArrivalMain.fxml");
            FXMLLoader loader = new FXMLLoader(url, SystemPreferences.getResourceBundle("arrivalLogIn"));

            Parent root = loader.load();
            Scene mainAppScene = new Scene(root, 1300, 530);
            Stage mainAppStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            mainAppScene.getStylesheets().add("/css/arrivalLogIn.css");

            mainAppStage.hide();
            mainAppStage.setScene(mainAppScene);
            mainAppStage.setTitle("Main - ArrivalOcto");
            mainAppStage.setResizable(true);
            mainAppStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
