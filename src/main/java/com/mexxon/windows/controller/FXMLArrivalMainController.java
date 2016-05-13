package com.mexxon.windows.controller;

import com.mexxon.ImportExportMain;
import com.mexxon.database.DBManger;
import com.mexxon.scheduler.JobManger;
import com.mexxon.utilities.Authentication;
import com.mexxon.utilities.WindowsDialogs;
import com.mexxon.windows.model.DBJobConfigEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    /**
     * @param JOB_MANGER for all scheduler Job from DB
     */
    private static JobManger JOB_MANGER = ImportExportMain.JOB_MANGER;

    /**
     * @param authentication static login var
     */
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
    private Button btnPause;
    @FXML
    private Button btnRun;
    @FXML
    private Button btnStop;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnLoadConfig;

    @FXML
    private TextField txtTable;
    @FXML
    private TextField txtSchema;
    @FXML
    private TextField txtStartTime;
    @FXML
    private TextField txtEndTime;
    @FXML
    private TextField txtScheduler;
    @FXML
    private TextField txtInterval;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEMail;

    @FXML
    private TableView<DBJobConfigEntity> tbvJobConfig;

    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcJobID;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcJobTyp;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcJobDescription;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcFileSource;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcFileExtension;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcSeparator;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcEncoding;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcHasHeader;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcType;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcPartner;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcCreated;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcLastModified;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcIsDeleted;
    @FXML
    private TableColumn<DBJobConfigEntity, String> tbcStatus;


    /**
     * @param bundle for Internationalization
     * @param dataJobConfig observer list for all config data in the DB
     */
    private ResourceBundle bundle;
    private ObservableList<DBJobConfigEntity> dataJobConfig;

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
        tbcJobID.setCellValueFactory(new PropertyValueFactory<>("jobID"));
        tbcJobTyp.setCellValueFactory(new PropertyValueFactory<>("jobTyp"));
        tbcJobDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
        tbcFileSource.setCellValueFactory(new PropertyValueFactory<>("fileSource"));
        tbcFileExtension.setCellValueFactory(new PropertyValueFactory<>("fileExtension"));
        tbcSeparator.setCellValueFactory(new PropertyValueFactory<>("separator"));
        tbcEncoding.setCellValueFactory(new PropertyValueFactory<>("encoding"));
        tbcHasHeader.setCellValueFactory(new PropertyValueFactory<>("hasHeader"));
        tbcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbcPartner.setCellValueFactory(new PropertyValueFactory<>("partner"));
        tbcCreated.setCellValueFactory(new PropertyValueFactory<>("created"));
        tbcLastModified.setCellValueFactory(new PropertyValueFactory<>("lastModified"));
        tbcIsDeleted.setCellValueFactory(new PropertyValueFactory<>("isDeleted"));
        tbcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        //Setup TableView
        //tbvJobConfig.getSelectionModel().setCellSelectionEnabled(true);
        tbvJobConfig.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Setup Data to TableView
        tbvJobConfig.setItems(dataJobConfig);
        addTableViewListener();

        //Ini JobManger
       /* try {
            JOB_MANGER = new JobManger();
            JOB_MANGER.startScheduler();
        } catch (SchedulerException e) {
            log.error("Fail to ini " + JOB_MANGER.getClass().getSimpleName() + ": " + e.getMessage());
        }*/

       // JOB_MANGER.startScheduler();
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
        btnPause.getTooltip().setText(bundle.getString("button.reset"));
        btnRun.getTooltip().setText(bundle.getString("button.run"));
        btnStop.getTooltip().setText(bundle.getString("button.stop"));
        btnUpdate.getTooltip().setText(bundle.getString("button.Update"));
        btnLoadConfig.getTooltip().setText(bundle.getString("button.LoadConfig"));
    }


    @FXML
    public void showAllStatus(ActionEvent actionEvent) {
        log.info("ShowAllStatus Clicked: " + ((Button) actionEvent.getSource()).getText());

    }


    @FXML
    public void closeApp(ActionEvent actionEvent) {
        log.info("Exit Clicked: " + ((Button) actionEvent.getSource()).getText());
        authentication.logout();
        new WindowsDialogs().closeWindowsConfirmation(log, null);
    }


    @FXML
    public void loadConfig(ActionEvent actionEvent) {
        log.info("LoadConfig Clicked: " + ((Button) actionEvent.getSource()).getText());
        if (dataJobConfig.isEmpty()) {
            getJobConfFromDB();
        } else {
            log.info("Job Configuration Table already loaded!!");
        }
    }


    @FXML
    public void runJob(ActionEvent actionEvent) {
        log.info("Run Clicked: " + ((Button) actionEvent.getSource()).getText());
        try {
            DBJobConfigEntity jobConfig = tbvJobConfig.getSelectionModel().getSelectedItem();
           // if (JOB_MANGER.isJobRunning(jobConfig)) {
                JOB_MANGER.runJob(jobConfig);
            //} else {
               // new WindowsDialogs().jobRunDialog();
            //}
        } catch (Exception e) {
            log.error("Job not started: " + e.getMessage());
        }
    }


    @FXML
    public void pauseJob(ActionEvent actionEvent) {
        log.info("Pause Clicked: " + ((Button) actionEvent.getSource()).getText());
        try {
            DBJobConfigEntity jobConfig = tbvJobConfig.getSelectionModel().getSelectedItem();
            if (JOB_MANGER.isJobRunning(jobConfig)) {
                JOB_MANGER.pauseJob(jobConfig);
            } else {
                new WindowsDialogs().jobStopResetDialog();
            }
        } catch (Exception e) {
            log.error("" + e.getMessage());
        }
    }


    @FXML
    public void stopJob(ActionEvent actionEvent) {
        log.info("Stop Clicked: " + ((Button) actionEvent.getSource()).getText());
        try {
            DBJobConfigEntity jobConfig = tbvJobConfig.getSelectionModel().getSelectedItem();
           // if (JOB_MANGER.isJobRunning(jobConfig)) {
                JOB_MANGER.stopJob(jobConfig);
            //} else {
              //  new WindowsDialogs().jobStopResetDialog();
            //}
        } catch (Exception e) {
            log.error("" + e.getMessage());
        }
    }


    @FXML
    public void updateConfigTable(ActionEvent actionEvent) {
        log.info("UpdateConfig Clicked: " + ((Button) actionEvent.getSource()).getText());
        getJobConfFromDB();
    }


    @FXML
    public void showLogInOut(ActionEvent actionEvent) {
        log.info("ShowLog Clicked: " + ((Button) actionEvent.getSource()).getText());

        try {
            URL url = getClass().getResource("/fxml/FXMLArrivalLogIn.fxml");
            FXMLLoader loader = new FXMLLoader(url, ImportExportMain.BUNDLE_LOGIN);

            Parent root = loader.load();
            Scene scene = new Scene(root, 400, 240);
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
            log.error(e.getMessage());
        }
    }


    @FXML
    public void menuCloseApp(ActionEvent actionEvent){
        log.info("Exit Clicked: " + ((MenuItem) actionEvent.getSource()).getText());
        authentication.logout();
        new WindowsDialogs().closeWindowsConfirmation(log, null);
    }


    @FXML
    public void menuAbout(ActionEvent actionEvent) {

    }


    @FXML
    public void menuLogin(ActionEvent actionEvent) {

    }


    private void getJobConfFromDB() {
        ArrayList<DBJobConfigEntity> temptDataList = new ArrayList<>();
        DBManger dbManger = new DBManger();
        temptDataList = dbManger.getJobConfigTable(Authentication.getDbConnection().getConnection(),
               ImportExportMain.BUNDLE_SQL.getString("table.job_config.getData"));

        dataJobConfig = FXCollections.observableArrayList(temptDataList);
        tbvJobConfig.setItems(dataJobConfig);
    }


    private void addTableViewListener() {
        tbvJobConfig.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                DBJobConfigEntity tempData = tbvJobConfig.getSelectionModel().getSelectedItem();
                //txtEndTime.setText(tempData.getEndTime()
                txtTable.setText(tempData.getTable());
                txtSchema.setText(tempData.getSchema());
                txtStartTime.setText(tempData.getStartTime());
                txtEndTime.setText(tempData.getEndTime());
                txtScheduler.setText(tempData.getScheduler());
                txtInterval.setText(tempData.getInterval());
                txtUserName.setText(tempData.getUserName());
                txtEMail.setText(tempData.getEmail());
            }
        });
    }
}
