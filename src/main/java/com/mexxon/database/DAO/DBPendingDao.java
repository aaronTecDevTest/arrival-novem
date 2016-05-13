package com.mexxon.database.DAO;
import com.mexxon.database.entity.DBOrderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;


/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 06.05.2016.
 * @since: 1.0
 * Package: com.mexxon.database
 */

public class DBPendingDao {
   // INSTANCE;
    private static final Logger log = LogManager.getLogger(DBPendingDao.class);

    private EntityManagerFactory emFactory;


    public DBPendingDao() {
        emFactory = Persistence.createEntityManagerFactory("importexport");
    }

    public DBPendingDao(String persistenceUnitName) {
        emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    /*
    public static void main(String[] args) {
        DBOrderEntity dbOrderEntity1 = new DBOrderEntity(
                (int)(System.currentTimeMillis()),"ddd","ddd","adf","fff","ddd", "adf","fff","sss",
                "adf","ss","fff", "adf","fff","ss", "adf","sss","fff");
        DBOrderEntity dbOrderEntity2 = new DBOrderEntity(
                (int)( (System.currentTimeMillis()+10)),"ddd","ddd","adf","fff","ddd", "adf","fff","sss",
                "adf","ss","fff", "adf","fff","ss", "adf","sss","fff");

        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(dbOrderEntity1);
        dataList.add(dbOrderEntity2);
        DBPendingDao pm = new DBPendingDao();
        pm.writeItemsToDB(dataList);

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
        pm.close();
    }
    */

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

    public ArrayList<Object> readIteamsFromDB(){
        ArrayList<Object> dataList = new ArrayList<>();

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        return dataList;
    }


    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }
}