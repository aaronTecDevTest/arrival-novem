package com.mexxon.windows.view;

import com.mexxon.utilities.SystemPreferences;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016.
 * @since: 1.0
 * Package: com.mexxon.windows.view
 */
public class ViewArrivalMainApp extends Application {
    private static final Logger log = LogManager.getLogger(ViewArrivalMainApp.class);

    @Override
    public void start(Stage primaryStage) throws IOException {
        SystemPreferences.getInstance();

        URL urlMain = getClass().getResource("/fxml/FXMLArrivalMain.fxml");
        URL applicationIcon = getClass().getResource("/icon/appIcons.png");
        Parent root = FXMLLoader.load(urlMain, SystemPreferences.getResourceBundle("arrivalMain"));

        Scene scene = new Scene(root);//, 1300, 530);
        scene.getStylesheets().add("/css/arrivalMain.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Maxxon: ImportExportTool");
        primaryStage.getIcons().add(new Image(applicationIcon.toString()));

        primaryStage.setResizable(false);

        //For all CloseRequest (CloseButton)
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // consume event
                event.consume();
                // show close dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit Confirmation");
                alert.setHeaderText("Do you really want to quit?");
                alert.initOwner( primaryStage);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    Platform.exit();
                }
            }
        });
        primaryStage.show();
    }

    public void run() {
        log.info("Main view show up!!");
        launch();
        log.info("Main view show down!!");
    }
}
