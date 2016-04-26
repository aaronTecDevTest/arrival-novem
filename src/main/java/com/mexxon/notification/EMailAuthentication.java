package com.mexxon.notification;

import com.mexxon.ImportExportMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.ResourceBundle;


/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 25.04.2016.
 * @since: 1.0
 * Package: com.mexxon.notification
 */
public class EMailAuthentication extends Authenticator {
    private static final Logger log = LogManager.getLogger(EMailAuthentication.class);
    private static ResourceBundle bundle = ImportExportMain.BUNDLE_CONFIG;

    private String emailAccount = bundle.getString("email.test.form.account");
    private String emailPassword =bundle.getString ("email.test.from.password");


    public EMailAuthentication() {
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        //return new PasswordAuthentication("r.mattiello@mexxon.com","mexmattiello");
        // 20150223--------------
        //Account serviceinfo@mexxon.com angelegt, PW ist "????".
        log.info("Account Authentication:");
        return new PasswordAuthentication(emailAccount,emailPassword);
        // 20150223--------------
        //return new PasswordAuthentication("drrmattiello@googlemail.com","deedle_doo");
        //return new PasswordAuthentication("r.mattiello@mexxon.com","deedle_doo");
    }
}
