package com.mexxon.windows.controller;

import com.mexxon.utilities.SystemPreferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private Menu mnuFile;
    @FXML
    private Menu mnuHelp;
    @FXML
    private MenuItem mnuExit;
    @FXML
    private MenuItem mnuLogin;
    @FXML
    private MenuItem mnuAbout;


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
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnLoadConfig;

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
        iniBundleResources();
    }

    private void iniBundleResources() {
        mnuFile.setText(bundle.getString("menu.title.file"));
        mnuHelp.setText(bundle.getString("menu.title.help"));

        mnuLogin.setText(bundle.getString("menu.title.login"));
        mnuExit.setText(bundle.getString("menu.title.exit"));
        mnuAbout.setText(bundle.getString("menu.title.about"));

        btnAllStates.getTooltip().setText(bundle.getString("button.allStatus"));
        btnExit.getTooltip().setText(bundle.getString("button.exit"));
        btnLogInOut.getTooltip().setText(bundle.getString("button.login"));
        btnReset.getTooltip().setText(bundle.getString("button.reset"));
        btnRun.getTooltip().setText(bundle.getString("button.run"));
        btnStop.getTooltip().setText(bundle.getString("button.stop"));
        btnUpdate.getTooltip().setText(bundle.getString("button.Update"));
        btnLoadConfig.getTooltip().setText(bundle.getString("button.LoadConfig"));
    }

    @FXML
    public void showAllStatus(ActionEvent actionEvent) {
        log.info("ShowAllStatus Clicked:" + ((Button)actionEvent.getSource()).getText());

    }

    @FXML
    public void closeApp(ActionEvent actionEvent) {
        log.info("Exit Clicked:" + ((Button)actionEvent.getSource()).getText());

        new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // consume event
                actionEvent.consume();
                // show close dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit Confirmation");
                alert.setHeaderText("Do you really want to quit?");
               // alert.initOwner(((Node)((Button) actionEvent.getSo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        };
    }

    @FXML
    public void loadConfig(ActionEvent actionEvent) {
        log.info("LoadConfig Clicked:" + ((Button)actionEvent.getSource()).getText());

    }

    @FXML
    public void resetJob(ActionEvent actionEvent) {
        log.info("Reset Clicked:" + ((Button)actionEvent.getSource()).getText());

    }

    @FXML
    public void runJob(ActionEvent actionEvent) {
        log.info("Run Clicked:" + ((Button)actionEvent.getSource()).getText());

    }

    @FXML
    public void stopJob(ActionEvent actionEvent) {
        log.info("Stop Clicked:" + ((Button)actionEvent.getSource()).getText());

    }

    @FXML
    public void updateConfigTable(ActionEvent actionEvent) {
        log.info("UpdateConfig Clicked:" + ((Button)actionEvent.getSource()).getText());

    }

    @FXML
    public void showLogInOut(ActionEvent actionEvent) {
        log.info("ShowLog Clicked:" + ((Button)actionEvent.getSource()).getText());

        try {
            URL url = getClass().getResource("/fxml/FXMLArrivalLogIn.fxml");
            FXMLLoader loader = new FXMLLoader(url, SystemPreferences.getResourceBundle("arrivalLogIn"));

            Parent root = loader.load();
            Scene scene = new Scene(root); //1300, 530);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            scene.getStylesheets().add("/css/arrivalLogIn.css");

            //primaryStage.hide();
            primaryStage.setScene(scene);
            primaryStage.setTitle("");
            primaryStage.setResizable(false);

            primaryStage.show();
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }
}
