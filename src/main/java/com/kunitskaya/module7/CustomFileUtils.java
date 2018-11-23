package com.kunitskaya.module7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class CustomFileUtils {
    private static final String START_MESSAGE = "Started moving file source: %s to: %s";

    public static void moveFileWithFileStream(String sourcePath, String targetPath) {
        LOGGER.info(String.format(START_MESSAGE, sourcePath, targetPath));

        File sourceFile = new File(sourcePath);

        try (FileInputStream inputStream = new FileInputStream(sourcePath);
             FileOutputStream outputStream = new FileOutputStream(targetPath + File.separator + sourceFile.getName())
        ) {
            int content = inputStream.read();
            while (content != -1) {
                outputStream.write(content);
                content = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

      // sourceFile.delete();
        LOGGER.info("Successfully moved file: " + sourceFile.getName());
    }
}
