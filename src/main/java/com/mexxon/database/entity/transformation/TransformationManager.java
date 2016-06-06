package com.mexxon.database.entity.transformation;

import com.mexxon.windows.model.DBColumnConfigEntity;
import com.mexxon.windows.model.DBJobConfigEntity;
import com.mexxon.windows.model.DBTransformationEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
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
    private DBJobConfigEntity jobConfig;
    private Map<String, DBTransformationEntity> jobTransformationRule;


    /**
     * @param objects
     * @param aClass
     * @param jobConfig
     */


    public TransformationManager(ArrayList<Object> objects, Class aClass, DBJobConfigEntity jobConfig){
        try {
            this.aClass = Class.forName("TransformationRule");
            this.aClassInstance = aClass.newInstance();
            this.jobConfig = jobConfig;
            this.jobTransformationRule = new HashMap<>();
            ini();

        } catch (ClassNotFoundException e) {
            log.error(e);
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
    }

    public void ini(){
        ArrayList<DBColumnConfigEntity>  tempColumnConfig = jobConfig.getColumnConfigEntities();
        for (DBColumnConfigEntity columnConfig: tempColumnConfig){
            jobTransformationRule.put(columnConfig.getOrgColumn(),columnConfig.getTransformationEntities());
        }
    }

    public void invokeMethode(){
        try {
            ArrayList<Method> mToInvokeInTransformation = getMethodToInvoke();
            ArrayList<Method> mToInvokeInObject;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Method> getMethodToInvoke() throws NoSuchMethodException {
        ArrayList<Method> tempMethod = new ArrayList<>();
        for (Map.Entry<String, DBTransformationEntity> entry : jobTransformationRule.entrySet()) {
            tempMethod.add(getMethod(entry.getKey()));
        }
        return tempMethod;
    }


    private Method getMethod(String methodName) throws NoSuchMethodException {
        switch(methodName){
            case "dateToString": {
                 return aClass.getMethod("dateToString", String.class);
            }
            case "stringToData": {
                return aClass.getMethod("stringToData", String.class, String.class);
            }
            case "addressCombine": {
                return aClass.getMethod("addressCombine", ArrayList.class, String.class);
            }
            case "addressSplitter": {
                return aClass.getMethod("addressSplitter", String.class, String.class);
            }
            default: {
                return null;
            }
        }
    }
}
