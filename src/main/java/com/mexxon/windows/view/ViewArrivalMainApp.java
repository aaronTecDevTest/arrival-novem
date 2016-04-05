package com.mexxon.windows.view;

import com.mexxon.utilities.SystemPreferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016.
 * @since: 1.0
 * Package: com.mexxon.windows.view
 */
public class ViewArrivalMainApp extends Application {
    private static final Logger log = LogManager.getLogger(ViewArrivalMainApp.class);

    private FXMLLoader loader;
    private Parent root;
    private URL urlMain;
    private URL applicationIcon;

    @Override
    public void start(Stage primaryStage) throws IOException {
        log.info("Start MainApp!!");
        SystemPreferences.getInstance();

        urlMain = getClass().getResource("/fxml/FXMLArrivalMain.fxml");
        applicationIcon = getClass().getResource("/icon/appIcons.png");
        loader = FXMLLoader.load(urlMain, SystemPreferences.getResourceBundle("arrivalMain"));
        root = loader.load();

        Scene scene = new Scene(root, 1300, 530);
        scene.getStylesheets().add("/css/arrivalMain.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mexxon - ImportExportTool");
        primaryStage.getIcons().add(new Image(applicationIcon.toString()));

        primaryStage.show();
        log.info("MainApp show up!!");
    }

    public void run() {
        launch();
    }
}
