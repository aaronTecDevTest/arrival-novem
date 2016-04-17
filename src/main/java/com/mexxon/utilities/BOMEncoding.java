package com.mexxon.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.04.2016.
 * @since: 1.0
 * Package: com.mexxon.utilities
 */

public class BOMEncoding {
    private static final Logger log = LogManager.getLogger(BOMEncoding.class);

    private FileInputStream fis = null;
    private InputStreamReader isr = null;
    private String s;

    public BOMEncoding() {
    }

    public static void main(String[] args) {
        BOMEncoding bomEncoding = new BOMEncoding();
        String url = "../arrival-novem/src/main/resources/testingData/fileToImport.csv";
        try {
            log.info(bomEncoding.fileEncodedAS(url));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public String readInput(String url) {
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(url);
            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char) ch);
            }
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        return buffer.toString();
    }

    public void encodeFileTo(String encoding, String file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos, "UTF8");
            out.write(s);
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public String fileEncodedAS(String filePath) throws IOException {
        try {
            // new input stream reader is created
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis);

            // the name of the character encoding returned
            s = isr.getEncoding();

        } catch (Exception e) {
            // print error
            log.error("The stream is already closed" + e.getMessage());
        } finally {
            // closes the stream and releases resources associated
            if (fis != null)
                fis.close();
            if (isr != null)
                isr.close();
        }
        return s;
    }
}