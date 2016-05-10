package com.mexxon.process;

import com.mexxon.database.DAO.DBOrderDao;
import com.mexxon.database.entity.DBOrderEntity;
import com.mexxon.scheduler.JobExecution;
import com.mexxon.windows.model.DBJobConfigEntity;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mexxon.process.EMProcessTyp.EXPORT;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.process
 */

public class CSVExportProcess  implements IFImportExport, Job, InterruptableJob{
    private static final Logger log = LogManager.getLogger(CSVExportProcess.class);
    private static final EMProcessTyp processTyp = EXPORT;

    private DBJobConfigEntity jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    private FileWriter fileWriter;
    private String filePath = "../arrival-novem/src/main/resources/testingData/orderWithHeaderWrite.csv";

    public CSVExportProcess() {
    }

    public CSVExportProcess(DBJobConfigEntity jobConfig){
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVExportProcess.class);
        this.processID = jobConfig.getJobID();
    }

    public void exportToCSV(){
        try {
            Character separator = jobConfig.getSeparator().charAt(0);

            CSVWriter writer = new CSVWriter(new FileWriter(filePath), separator);
            ArrayList<DBOrderEntity> dataList = new DBOrderDao().readItemsFromDB();
            List<DBOrderEntity> dataListString =  dataList.subList(0,dataList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDBJobConfigTable(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVExportMexxonProcess.class);
        this.processID = jobConfig.getJobID();
    }

    @Override
    public DBJobConfigEntity getJobConfig() {
        return jobConfig;
    }

    @Override
    public void setJobConfig(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
    }

    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobExecution.jobExecution(jobContext,log);
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        JobExecution.jobInterruption();
    }

    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }

    @Override
    public JobBuilder getJobBuilder() {
        return jobBuilder;
    }

    @Override
    public void runJob() {
        exportToCSV();
    }
}