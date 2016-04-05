package com.mexxon.windows.view;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016
 * @since: 1.0
 * Package: com.arrival.windows.view
 */

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
        log.info("Start App with LogIn");
        SystemPreferences.getInstance();
        url = getClass().getResource("/fxml/FXMLArrivalLogIn.fxml");
        applicationIcon = getClass().getResource("/icon/appIcons.png");
        loader = new FXMLLoader(url, SystemPreferences.getResourceBundle("arrivalLogIn"));
        root = loader.load();

        Scene scene = new Scene(root);//, 400, 300);
        scene.getStylesheets().add("/css/arrivalLogIn.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mexxon - LogIn");
        primaryStage.getIcons().add(new Image(applicationIcon.toString()));

        primaryStage.setResizable(false);
        primaryStage.show();
        log.info("LogIn show up");
    }

    public void run() {
        launch();
    }
}
