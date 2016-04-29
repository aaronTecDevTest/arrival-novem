package com.mexxon.windows.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 29.04.2016.
 * @since: 1.0
 * Package: com.mexxon.model
 */

/**
 CREATE TABLE `order` (
 `ClientOrder` varchar(100) DEFAULT NULL,
 `POI` varchar(100) DEFAULT NULL,
 `ProductID` varchar(100) DEFAULT NULL,
 `ClientAccountID` varchar(100) DEFAULT NULL,
 `AcountID` varchar(100) DEFAULT NULL,
 `Gender` varchar(100) DEFAULT NULL,
 `LastName` varchar(100) DEFAULT NULL,
 `MaidenName` varchar(100) DEFAULT NULL,
 `FirstName` varchar(100) DEFAULT NULL,
 `Street` varchar(100) DEFAULT NULL,
 `Hause` varchar(100) DEFAULT NULL,
 `HauseADD` varchar(100) DEFAULT NULL,
 `ZIP` varchar(100) DEFAULT NULL,
 `City` varchar(100) DEFAULT NULL,
 `Country` varchar(100) DEFAULT NULL,
 `DOB` varchar(100) DEFAULT NULL,
 `Phone` varchar(100) DEFAULT NULL,
 `Email` varchar(100) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
public class DBOrderEntity {
    private static final Logger log = LogManager.getLogger(DBOrderEntity.class);

    private String ClientOrder;
    private String POI;
    private String ProductID;
    private String ClientAccountID;
    private String AccountID;
    private String Gender;
    private String LastName;
    private String MaidenName;
    private String FirstName;
    private String Street;
    private String House;
    private String HouseADD;
    private String ZIP;
    private String City;
    private String Country;
    private String DOB;
    private String Phone;
    private String Email;

    public DBOrderEntity() {
    }

    public DBOrderEntity(String clientOrder, String POI, String productID, String clientAccountID, String accountID, String gender, String lastName, String maidenName, String firstName, String street, String house, String houseADD, String ZIP, String city, String country, String DOB, String phone, String email) {
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

    public String getClientOrder() {
        return ClientOrder;
    }

    public void setClientOrder(String clientOrder) {
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