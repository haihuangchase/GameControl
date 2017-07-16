package txt;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KeyUtil {
    private static Logger LOGGER = LogManager.getLogger(KeyUtil.class);
    private static final String DELIMINATOR = "\t";
    private static final String KEY_DELIMINATOR = "\\+";
    private static final String PATH_ROOT = "/tmp/vAttack/";

    private Map<String, List<Integer>> keyMap = new HashMap<>();

    public KeyUtil(String gameName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(PATH_ROOT + gameName));

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(DELIMINATOR);
                String skills = line[0];
                List<Integer> keyCodes = getKeyCodes(line[1]);
                keyMap.put(skills, keyCodes);
              //  LOGGER.info(keyCodes.toString());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
     //   LOGGER.setLevel(Level.INFO);
    }

    public static Map<String, String> load(String gameName) {
        Scanner scanner = null;
        Map<String, String> keyMap = new HashMap<>();
        try {
            scanner = new Scanner(new File(PATH_ROOT + gameName));

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(DELIMINATOR);
                keyMap.put(line[0], line[1]);
                //  LOGGER.info(keyCodes.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return keyMap;
    }

    public static void save(String gameName, Map<String, String> keyMapping) throws RuntimeException {
        //Create temp directory.
        File tempDir = new File(PATH_ROOT);
        if (!tempDir.exists()) {
            if (tempDir.mkdir()) {
                LOGGER.info("Created temp directory: " + tempDir.getPath() + " .");
            } else {
                LOGGER.error("Error creating temp directory at " + tempDir.getPath());
                throw new RuntimeException("Error creating temp directory.");
            }
        } else {
            LOGGER.info("Temp directory already exists. Skipping mkdir.");
        }

        //Write data to temp file.
        BufferedWriter writer = null;
        File tempFile;
        try {
            tempFile = new File(PATH_ROOT + gameName);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "Cp1252"));
            for (Map.Entry<String, String> entry : keyMapping.entrySet()) {
                writer.write(entry.getKey() + "\t" + entry.getValue());
                writer.newLine();
            }
            LOGGER.info("Finished writing to temporary file.");
            LOGGER.info("Record number: " + keyMapping.size());
        } catch (IOException e) {
            LOGGER.error("Failed writing to temp file.");
            throw new RuntimeException("Failed writing to temp file", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to close file output stream.", e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        KeyUtil keyUtil = new KeyUtil("a.tsv");
        keyUtil.pressKey("get");
    }

    public void pressKey(String skill) {
        List<Integer> keyCodes = keyMap.get(skill);
        LOGGER.info(keyCodes.toString());
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(250);
            //press key
            for (int keyCode : keyCodes) {
                LOGGER.info("Pressing " + keyCode);
                robot.keyPress(keyCode);
            }
            //release key
            for (int keyCode : keyCodes) {
                LOGGER.info("Releasing " + keyCode);
                robot.keyRelease(keyCode);
            }
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    List<Integer> getKeyCodes (String keyList) {
        String[] keys = keyList.split(KEY_DELIMINATOR);
        List<Integer> keyCodes = new LinkedList<>();
        for (String key : keys) {
            keyCodes.add(VKMapping.KEY_MAPPING.get(key.toUpperCase()));
        }
        return keyCodes;
    }
}