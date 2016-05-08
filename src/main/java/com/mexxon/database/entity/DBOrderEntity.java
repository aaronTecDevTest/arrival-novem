package com.mexxon.database.entity;

import com.opencsv.bean.CsvBind;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 27.05.2016.
 * @since: 1.0
 * Package:  com.mexxon.database.DAO
 */

/**
 *
 */

@Entity
@Table(name = "order_table")
public class DBOrderEntity implements IFMapping {
    private static final Logger log = LogManager.getLogger(DBOrderEntity.class);


    @CsvBind(required = true)
    @Id()

    private Integer ClientOrder;
    @CsvBind
    private String POI;
    @CsvBind
    private String ProductID;
    @CsvBind
    private String ClientAccountID;
    @CsvBind
    private String AccountID;
    @CsvBind
    private String Gender;
    @CsvBind
    private String LastName;
    @CsvBind
    private String MaidenName;
    @CsvBind
    private String FirstName;
    @CsvBind
    private String Street;
    @CsvBind
    private String House;
    @CsvBind
    private String HouseADD;
    @CsvBind
    private String ZIP;
    @CsvBind
    private String City;
    @CsvBind
    private String Country;
    @CsvBind
    private String DOB;
    @CsvBind
    private String Phone;
    @CsvBind
    private String Email;

    public DBOrderEntity() {
    }

    public DBOrderEntity(int clientOrder, String POI, String productID, String clientAccountID, String accountID,
                         String gender, String lastName, String maidenName, String firstName, String street, String house,
                         String houseADD, String ZIP, String city, String country, String DOB, String phone, String email) {
        ClientOrder = clientOrder;
        this.POI = POI;
        ProductID = productID;
        ClientAccountID = clientAccountID;
        AccountID = accountID;
        Gender = gender;
        LastName = lastName;
        MaidenName = maidenName;
        FirstName = firstName;
        Street = street;
        House = house;
        HouseADD = houseADD;
        this.ZIP = ZIP;
        City = city;
        Country = country;
        this.DOB = DOB;
        Phone = phone;
        Email = email;
    }

    public Integer getClientOrder() {
        return ClientOrder;
    }

    public void setClientOrder(Integer clientOrder) {
        ClientOrder = clientOrder;
    }

    public String getPOI() {
        return POI;
    }

    public void setPOI(String POI) {
        this.POI = POI;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getClientAccountID() {
        return ClientAccountID;
    }

    public void setClientAccountID(String clientAccountID) {
        ClientAccountID = clientAccountID;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMaidenName() {
        return MaidenName;
    }

    public void setMaidenName(String maidenName) {
        MaidenName = maidenName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getHouseADD() {
        return HouseADD;
    }

    public void setHouseADD(String houseADD) {
        HouseADD = houseADD;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public HeaderColumnNameMappingStrategy<DBOrderEntity> getMappingStrategy() {
        HeaderColumnNameMappingStrategy<DBOrderEntity> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(DBOrderEntity.class);
        return strategy;
    }

    public static void main(String[] args) {
        DBOrderEntity dbOrderEntity = new DBOrderEntity();

        Map<String, Integer> testData = new HashMap();
        testData.put("ClientOrder",0);
        testData.put("ZIP",3);
        testData.put("City",5);
        testData.put("Gender",6);
        testData.put("Street",7);
        testData.put("HouseADD",9);
        testData.put("Phone",10);
        testData.put("POI",11);
        dbOrderEntity.getConfigHeader(testData);
    }

    @Override
    public String[] getConfigHeader(Map<String,Integer> columnConfig) {
        String defaultHeader[] = getDefaultHeader();
        String configHeader [] = new String[defaultHeader.length];

        for (int i = 0; i < defaultHeader.length ; i++) {
            if(columnConfig.get( defaultHeader[i])!= null ){
                int p = columnConfig.get(defaultHeader[i]);
                configHeader[p] = defaultHeader[i];
            }
        }
        return configHeader;
    }

    @Override
    public String[] getDefaultHeader() {
        return new String[]{"ClientOrder", "POI", "ProductID",
                "ClientAccountID", "AccountID", "Gender", "LastName",
                "MaidenName", "FirstName", "Street", "House", "HouseADD",
                "ZIP", "City", "County", "DOB", "Phone", "Email"};
    }

    @Override
    public String toString() {
        return "DBOrderEntity{" +
                "ClientOrder='" + ClientOrder + '\'' +
                ", POI='" + POI + '\'' +
                ", ProductID='" + ProductID + '\'' +
                ", ClientAccountID='" + ClientAccountID + '\'' +
                ", AccountID='" + AccountID + '\'' +
                ", Gender='" + Gender + '\'' +
                ", LastName='" + LastName + '\'' +
                ", MaidenName='" + MaidenName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Street='" + Street + '\'' +
                ", House='" + House + '\'' +
                ", HouseADD='" + HouseADD + '\'' +
                ", ZIP='" + ZIP + '\'' +
                ", City='" + City + '\'' +
                ", Country='" + Country + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}