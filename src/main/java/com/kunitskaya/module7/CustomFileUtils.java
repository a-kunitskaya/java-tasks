package com.kunitskaya.module7;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class CustomFileUtils {
    private static final String START_MESSAGE = "Started moving file source: %s to: %s";
    private static final String FINISH_MESSAGE = "Successfully moved file: %s with: %s";

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
            e.printStackTrace(
            );
        }

        if (sourceFile.exists()) {
            sourceFile.delete();
            LOGGER.info(String.format(FINISH_MESSAGE, sourceFile.getName(), "File Stream"));
        }
    }

    public static void moveFileWithBufferedStream100(String sourceFilePath, String targetDir) {
        LOGGER.info(String.format(START_MESSAGE, sourceFilePath, targetDir));

        File sourceFile = new File(sourceFilePath);

        try (
                FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
                FileOutputStream fileOutputStream = new FileOutputStream(targetDir + File.separator + sourceFile.getName());
                BufferedInputStream inputStream = new BufferedInputStream(fileInputStream, 100);
                BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream, 100)
        ) {
            int content = inputStream.read();
            while (content != -1) {
                outputStream.write(content);
                content = inputStream.read();
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sourceFile.exists()) {
            sourceFile.delete();
            LOGGER.info(String.format(FINISH_MESSAGE, sourceFile.getName(), "Buffered Stream, size 100"));
        }

    }

    public static void moveFileWithFileChannel(String pathFrom, String pathTo) {

    }

    public static void moveFileWithNIO(String pathFrom, String pathTo) {

    }

    public static void createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileUtils.writeStringToFile(file, "File content", Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
