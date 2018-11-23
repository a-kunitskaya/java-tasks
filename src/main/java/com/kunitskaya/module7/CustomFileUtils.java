package com.kunitskaya.module7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class CustomFileUtils {
    private static final String START_MESSAGE = "Started moving file from: %s to: %s";

    public static void moveFileWithFileStream(String sourcePath, String targetPath) {
        LOGGER.info(String.format(START_MESSAGE, sourcePath, targetPath));

        try (FileInputStream inputStream = new FileInputStream(sourcePath);
             FileOutputStream outputStream = new FileOutputStream(targetPath)
        ) {
            int content = inputStream.read();
            while (content != -1) {
                outputStream.write(content);
                content = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Successfully moved file");
    }
}
