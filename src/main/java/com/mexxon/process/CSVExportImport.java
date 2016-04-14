package com.mexxon.process;

import com.mexxon.windows.model.DBJobConfigTable;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 14.04.2016.
 * @since: 1.0
 * Package: com.mexxon.process
 */
public class CSVExportImport {
    protected String from;
    protected String to;
    protected String startTime;
    protected String endTime;
    protected String scheduler;
    protected String processUser;
    protected String separator;
    protected DBJobConfigTable jobConfig;

    public CSVExportImport() {
    }

    public CSVExportImport(String from, String to, String startTime, String endTime, String scheduler, String processUser,String separator, DBJobConfigTable jobConfig) {
        this.from = from;
        this.to = to;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduler = scheduler;
        this.processUser = processUser;
        this.separator = separator;
        this.jobConfig = jobConfig;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getScheduler() {
        return scheduler;
    }

    public void setScheduler(String scheduler) {
        this.scheduler = scheduler;
    }

    public String getProcessUser() {
        return processUser;
    }

    public void setProcessUser(String processUser) {
        this.processUser = processUser;
    }

    public DBJobConfigTable getJobConfig() {
        return jobConfig;
    }

    public void setJobConfig(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public String toString() {
        return "CSVExportImport: {" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", scheduler='" + scheduler + '\'' +
                ", processUser='" + processUser + '\'' +
                ", separator='" + separator + '\'' +
                ", jobConfig=" + jobConfig +
                '}';
    }
}