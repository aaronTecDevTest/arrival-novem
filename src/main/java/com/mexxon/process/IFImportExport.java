package com.mexxon.process;

import com.mexxon.windows.model.DBJobConfigEntity;
import org.quartz.JobBuilder;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 20.04.2016.
 * @since: 1.0
 * Package: com.mexxon.process
 */


public interface IFImportExport {
    void setProcessID (Long processID);

    void runJob();

    JobBuilder  getJobBuilder();

    DBJobConfigEntity getJobConfig();

    void setJobConfig (DBJobConfigEntity jobConfig);
    //void stopJob();
}