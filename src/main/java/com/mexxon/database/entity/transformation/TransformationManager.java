package com.mexxon.database.entity.transformation;

import com.mexxon.windows.model.dao.DBJobConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 21.04.2016.
 * @since: 1.0
 * Package: com.mexxon.database.entity.transformation
 */

public class TransformationManager {
    private static final Logger log = LogManager.getLogger(TransformationManager.class);

    private Class aClass;
    private Object aClassInstance;
    private Method method;
    private DBJobConfig jobConfig;


    /**
     *
     * @param objects
     * @param aClass
     * @param jobConfig
     */
    public TransformationManager(ArrayList<Object> objects, Class aClass, DBJobConfig jobConfig){
        try {
            this.aClass = Class.forName("TransformationRule");
            this.aClassInstance = aClass.newInstance();
            this.jobConfig = jobConfig;
        } catch (ClassNotFoundException e) {
            log.error(e);
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
    }


    public void invokeMethode(){

    }

}
