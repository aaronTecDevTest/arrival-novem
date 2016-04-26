package com.mexxon.notification;

import com.mexxon.ImportExportMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.notification
 */
//http://www.journaldev.com/2532/java-program-to-send-email-using-smtp-gmail-tls-ssl-attachment-image-example
public class EMailSend {
    private static final Logger log = LogManager.getLogger(EMailSend.class);
    private static ResourceBundle bundle = ImportExportMain.BUNDLE_CONFIG;

    private Properties properties;
    private String toAddress;
    private String ccAddress;
    private String bccAddress;
    private String fromAddress;

    private String host;
    private String port;

    /**
     * Standard Constructor
     */
    public EMailSend() {
        iniSMTPServer();
    }

    /**
     * Standard General
     */
    public EMailSend(String fromAddress, String toAddress) {
        this.fromAddress    = fromAddress;
        this.toAddress      = toString();
        iniSMTPServer();
    }

    public static void main(String[] args) {
        String from = "aarontectesting@gmail.com";
        String to = "aarontectesting@gmail.come";

        EMailSend eMailSend = new EMailSend();
        eMailSend.sendSMTPEmail(from,to,"testing", "hallo hallo aaron");
    }

    private void iniSMTPServer(){
        properties  = System.getProperties();
        host        = bundle.getString("email.test.host");
        port        = bundle.getString("email.test.port");


        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port",port);

        //Debug ON
        properties.put("mail.debug", "true");

        // TLS
        properties.put("mail.smtp.starttls.enable", "true");

        //SSL
        properties.put("mail.smtp.ssl.enable", "true");
    }

    public void sendSMTPEmail(String subject, String textMessage){
        sendSMTPEmail(fromAddress, toAddress, subject,textMessage);
    }

    public void sendSMTPEmail(String from, String to , String subject, String textMessage) {
        Session session = Session.getInstance(properties, new EMailAuthentication());
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from,from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);

            // set reply to email address
            InternetAddress[] replyToAddress = new InternetAddress[1];
            replyToAddress[0] = new InternetAddress(from);
            msg.setReplyTo(replyToAddress);

            msg.setSubject(subject);
            msg.setSentDate(new Date());

            msg.setText(textMessage);

            Transport.send(msg);

            log.info("is sending");
        }
        catch (MessagingException e) {
            log.error("EMail error: " + e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendSMTPEmailWithAttachments(String subject, String textMessage,File [] attachments ){
        sendSMTPEmailWithAttachments(fromAddress, toAddress, subject, textMessage, attachments);
    }

    public void sendSMTPEmailWithAttachments(String from, String to, String subject, String textMessage, File[] attachments) {
        Session session = Session.getInstance(properties, new EMailAuthentication());
        Multipart multipart = new MimeMultipart();

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());

            // set reply to email address
            InternetAddress[] replyToAddress = new InternetAddress[1];
            replyToAddress[0] = new InternetAddress(from);
            message.setReplyTo(replyToAddress);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(textMessage);

            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            for( int i = 0; i < attachments.length; i++ ) {
                if (attachments[i]!=null && attachments[i].exists()) {
                    messageBodyPart = new MimeBodyPart();
                    FileDataSource fileDataSource =new FileDataSource(attachments[i]);
                    messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
                    messageBodyPart.setFileName(attachments[i].getName());
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            // Put parts in message
            message.setContent(multipart);
            // Send the message
            Transport.send( message );

        } catch (MessagingException e) {
            log.error("EMail with Attachments error: " + e.getMessage());
            log.error("EMail with Attachments error: " + e.getStackTrace().toString());
        }
    }

    public void setupMailServer(String serverTyp,String host){
        properties.setProperty(serverTyp,host);
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
