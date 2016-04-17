package com.mexxon.utilities;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 02.05.2016.
 * @since: 1.0
 * Package: com.arrival.utilities
 */

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class WindowsDialogs {
    private ResourceBundle bundle = SystemPreferences.getResourceBundle("bundleDialogs");
    private URL iconURL =  getClass().getResource("/icon/appIcons.png");

    public WindowsDialogs() {
    }

    public  String setTestsuiteNameDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Testsuite name");
        dialog.setHeaderText("Enter the Testsuite name:");

        Optional<String> result = dialog.showAndWait();
        String entered;

        if (result.isPresent()) {
            entered = result.get();
        } else {
            entered = "";
        }
        return entered;
    }

    public void jobRunDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Job is all ready running!");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(iconURL.toString()));
        alert.showAndWait();
    }

    public void jobRestDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Job is not running!");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(iconURL.toString()));
        alert.showAndWait();
    }

    public void jobStopDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Job is not running!");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(iconURL.toString()));
        alert.showAndWait();
    }

    public void closeWindowsConfirmation(Logger log, Stage primaryStage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Do you really want to quit?");
        alert.initOwner( primaryStage);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(iconURL.toString()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                Authentication.getInstance().getDbConnection().closeConnection();
            }
            catch (Exception e){
                log.error(e.getMessage());
            }
            Platform.exit();
        }
    }
}
