package com.mexxon.windows.controller;

import com.mexxon.database.DBManger;
import com.mexxon.utilities.Authentication;
import com.mexxon.utilities.SystemPreferences;
import com.mexxon.windows.model.DBJobConfigTable;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private Authentication authentication = Authentication.getInstance();

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


    @FXML
    private TextField txtTo;
    @FXML
    private TextField txtStartTime;
    @FXML
    private TextField txtFrom;
    @FXML
    private TextField txtScheduler;
    @FXML
    private TextField txtProcessUser;
    @FXML
    private TextField txtJobTyp;
    @FXML
    private TextField txtExpiredTime;
    @FXML
    private TextField txtEndTime;


    @FXML
    private TableView <DBJobConfigTable> tbvJobConfig;


    @FXML
    private TableColumn<DBJobConfigTable, String> tbcJobID;
    @FXML
    private TableColumn<DBJobConfigTable, String> tbcJobTyp;
    @FXML
    private TableColumn<DBJobConfigTable, String> tbcExportSQL;


    /**
     * @param bundle For Internationalization
     */
    private ResourceBundle bundle;
    private ObservableList<DBJobConfigTable> dataJobConfig;

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
        //Ini BundleResources
        bundle = resources;
        iniBundleResources();

        //Setup Table-Data Objects
        dataJobConfig = FXCollections.observableArrayList();

        //Setup Table-Column Properties
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable, String>("job_id"));
        tbcJobTyp.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable, String>("job_typ"));
        tbcExportSQL.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable, String>("export_sql"));
        /*tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));
        tbcJobID.setCellValueFactory(new PropertyValueFactory<DBJobConfigTable,String>(""));*/

        //Setup TableView
        //tbvJobConfig.getSelectionModel().setCellSelectionEnabled(true);
        tbvJobConfig.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //Setup Data to TableView
        tbvJobConfig.setItems(dataJobConfig);
        addTableViewListener();
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
        authentication.logout();

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
        log.info("LoadConfig Clicked:" + ((Button) actionEvent.getSource()).getText());
        if (dataJobConfig.isEmpty()) {
            ArrayList<DBJobConfigTable> temptDataList = new ArrayList<>();
            DBManger dbManger = new DBManger();
            temptDataList =  dbManger.getJobConfigTable(Authentication.getDbConnection().getConnection(),
                                        SystemPreferences.getResourceBundle("arrivalSQL").getString("table.job_config.getData"));

            dataJobConfig = FXCollections.observableArrayList(temptDataList);
            tbvJobConfig.setItems(dataJobConfig);
        } else {
            log.info("Job Configuration Table already loaded!!");
        }
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
            Scene scene = new Scene(root,400, 240);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            scene.getStylesheets().add("/css/arrivalLogIn.css");

            //primaryStage.hide();
            primaryStage.setScene(scene);
            primaryStage.setTitle("");
            primaryStage.setResizable(false);

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
            primaryStage.show();
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }

    private void addTableViewListener() {
        tbvJobConfig.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                DBJobConfigTable tempData = tbvJobConfig.getSelectionModel().getSelectedItem();
                txtEndTime.setText(tempData.getExport_sql());
                txtExpiredTime.setText(tempData.getExport_sql());
                txtFrom.setText(tempData.getExport_sql());
                txtTo.setText(tempData.getExport_sql());
                txtJobTyp.setText(tempData.getExport_sql());
                txtScheduler.setText(tempData.getExport_sql());
                txtProcessUser.setText(tempData.getExport_sql());
            }
        });
    }
}
