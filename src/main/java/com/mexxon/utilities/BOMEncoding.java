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
        try {
            log.info(bomEncoding.fileEncodedAS());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readInput() {
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream("test.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char) ch);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return buffer.toString();
    }

    public void encodeFileTo(String encoding) {
        try {
            FileOutputStream fos = new FileOutputStream("test.txt");
            Writer out = new OutputStreamWriter(fos, "UTF8");
            out.write(s);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fileEncodedAS() throws IOException {
        try {
            // new input stream reader is created
            fis = new FileInputStream("../arrival-novem/src/main/resources/testingData/fileToImport.csv");
            isr = new InputStreamReader(fis);

            // the name of the character encoding returned
            s = isr.getEncoding();
            System.out.print("Character Encoding: " + s);

        } catch (Exception e) {
            // print error
            System.out.print("The stream is already closed");
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