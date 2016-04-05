package com.mexxon.notification;

import com.mexxon.utilities.SystemPreferences;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Properties;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.notification
 */

public class EMail {
    private static final Logger log = LogManager.getLogger(EMail.class);

    private Properties properties;
    private String toAddress;
    private String ccAddress;
    private String bccAddress;
    private String fromAddress;
    private String serverTyp;
    private String host;

    private String message;
    private ArrayList<File> attachmentList;

    /**
     * Standard Constructor
     */
    public EMail() {
        fromAddress = "aaron.kutekidila@stroeer.de";
        toAddress   = "aaron.kutekidila@stroeer.de";
        bccAddress  = "aaron.kutekidila@stroeer.de";
        ccAddress   = "aaron.kutekidila@stroeer.de";
        iniEmail();
    }

    /**
     * Standard General
     */
    public EMail(String fromAddress, String toAddress) {
        this.fromAddress    = fromAddress;
        this.toAddress      = toString();
        iniEmail();
    }

    private void iniEmail(){
        properties     = SystemPreferences.getProperties();
        serverTyp   = "mail.smtp.host";
        host        = "localhost";
        //properties.setProperty(serverTyp,host);
        properties.put(serverTyp,"587");
        properties.put(serverTyp,"true");
        properties.put("mail.smtp.starttls.enable","true");



        message ="";
        attachmentList = new ArrayList<>();
    }

    public void setupMailServer(String serverTyp,String host){
        properties.setProperty(serverTyp,host);
    }

    public boolean sendEmail(){
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties,null);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromAddress));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return false;
    }

    public void createEmail(String message) {
    }

    public void createEmail(String message, File attachment){
    }

    public void cerateEmail(String message, ArrayList<File> attachments){
    }


    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
}
