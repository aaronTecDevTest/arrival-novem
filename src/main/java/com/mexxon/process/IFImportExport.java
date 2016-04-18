package com.mexxon.process;

import com.mexxon.windows.model.DBJobConfigTable;
import org.quartz.JobBuilder;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */


public interface IFImportExport {
    void runProcess();
    void setProcessID (Long processID);
    void setJobConfig (DBJobConfigTable jobConfig);
    JobBuilder  getJobBuilder();
    DBJobConfigTable getJobConfig();
}
