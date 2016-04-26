package com.mexxon.windows.view;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016
 * @since: 1.0
 * Package: com.arrival.windows.view
 */

import com.mexxon.ImportExportMain;
import com.mexxon.utilities.SystemPreferences;
import com.mexxon.utilities.WindowsDialogs;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;


/**
 * Login Class to get more authorisation form arrival App and more
 * functions.
 **/
public class ViewArrivalLogIn extends Application {
    private static final Logger log = LogManager.getLogger(ViewArrivalLogIn.class);
    private FXMLLoader loader;
    private Parent root;
    private URL url;
    private URL applicationIcon;

    @Override
    public void start(Stage primaryStage) throws IOException {
        SystemPreferences.getInstance();
        url = getClass().getResource("/fxml/FXMLArrivalLogIn.fxml");
        applicationIcon = getClass().getResource("/icon/appIcons.png");
        loader = new FXMLLoader(url, ImportExportMain.BUNDLE_LOGIN);
        root = loader.load();

        Scene scene = new Scene(root, 400, 240);
        scene.getStylesheets().add("/css/arrivalLogIn.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mexxon: ImportExportTool - LogInOut");
        primaryStage.getIcons().add(new Image(applicationIcon.toString()));
        primaryStage.setResizable(false);
        /*
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);*/
        primaryStage.show();

        //For all CloseRequest (CloseButton)
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // consume event
                event.consume();
                // show close dialog
                new WindowsDialogs().closeWindowsConfirmation(log, primaryStage);
            }
        });
    }

    public void run() {
        log.info("LogIn show up");
        launch();
        log.info("LogIn show down");
    }
}
