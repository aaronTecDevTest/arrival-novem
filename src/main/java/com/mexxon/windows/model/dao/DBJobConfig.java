package com.mexxon.windows.model.dao;

import com.mexxon.windows.model.DBJobConfigEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 19.05.2016.
 * @since: 1.0
 * Package: com.mexxon.window.model.dao
 */

public class DBJobConfig {
   // INSTANCE;
    private static final Logger log = LogManager.getLogger(DBJobConfig.class);

    private EntityManagerFactory emFactory;


    public DBJobConfig() {
        emFactory = Persistence.createEntityManagerFactory("importexport_config");
    }

    public DBJobConfig(String persistenceUnitName) {
        emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public static void main(String[] args) {
        DBJobConfigEntity dbJobConfig1 = new DBJobConfigEntity(
                System.currentTimeMillis(),"ddd","ddd","adf","fff","ddd", "adf","fff","sss",
                "adf","ss","fff", "adf","fff","ss", "adf","sss","fff","dfsf","fsdf","sdfsf",(long) 20);
        DBJobConfigEntity dbJobConfig2 = new DBJobConfigEntity(
                (System.currentTimeMillis()+10),"ddd","ddd","adf","fff","ddd", "adf","fff","sss",
                "adf","ss","fff", "adf","fff","ss", "adf","sss","fff","sdf","sdfs","sfs",(long)20);

        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(dbJobConfig1);
        dataList.add(dbJobConfig2);
        DBJobConfig em = new DBJobConfig();

       //pm.writeItemsToDB(dataList);

        ArrayList<DBJobConfigEntity> dataList2 = em.readItemsFromDB();
        for (int i = 0; i <dataList.size() ; i++) {
            System.out.printf(String.valueOf(dataList2.get(i).getJobID()));
        }

        /*EntityManager em = pm.getEntityManager();
        em.getTransaction().begin();

        em.persist(dbOrderEntity1);
        em.flush();
        em.clear();

        em.persist(dbOrderEntity2);
        em.flush();
        em.clear();

        em.getTransaction().commit();

        em.close();
        pm.close();*/
    }

    public void writeItemsToDB(ArrayList<Object> dataList){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        for (Object data: dataList) {
            em.persist(data);
            em.flush();
            em.clear();
        }

        em.getTransaction().commit();
        em.close();
        close();
    }

    public ArrayList<DBJobConfigEntity> readItemsFromDB(){
        ArrayList<DBJobConfigEntity> dataList = new ArrayList<>();

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        dataList = (ArrayList<DBJobConfigEntity>) em.createQuery("from DBJobConfigEntity").getResultList();
       //dataList = (ArrayList<DBOrderEntity>) em.createNativeQuery("select * from order_table").getResultList();
        em.getTransaction().commit();
        em.close();
        return dataList;
    }

    public void create (DBJobConfigEntity jobConfigEntity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(jobConfigEntity);
        em.getTransaction().commit();
        em.close();
        close();
    }

    public void delete(DBJobConfigEntity jobConfigEntity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        if (em.contains(jobConfigEntity))
            em.remove(jobConfigEntity);
        else
            em.remove(em.merge(jobConfigEntity));
        em.getTransaction().commit();
        em.close();
        close();
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }
}