package com.kunitskaya.module7;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class CustomFileUtils {
    private static final String START_MESSAGE = "Started moving file source: %s to: %s";
    private static final String FINISH_MESSAGE = "Successfully moved file: %s with: %s";
    private static final String MAKE_DIRECTORY_MESSAGE = "Created directory: %s";

    public static void moveFileWithFileStream(String sourceFilePath, String targetDir) {
        LOGGER.info(String.format(START_MESSAGE, sourceFilePath, targetDir));

        createDirectory(targetDir);

        File sourceFile = new File(sourceFilePath);

        try (FileInputStream inputStream = new FileInputStream(sourceFilePath);
             FileOutputStream outputStream = new FileOutputStream(targetDir + File.separator + sourceFile.getName())
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
            String usedMethod = "File Stream";
            LOGGER.info(String.format(FINISH_MESSAGE, sourceFile.getName(), usedMethod));
        }
    }

    public static void moveFileWithBufferedStream100(String sourceFilePath, String targetDir) {
        LOGGER.info(String.format(START_MESSAGE, sourceFilePath, targetDir));

        createDirectory(sourceFilePath);
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
            String usedMethod = "Buffered Stream, size 100";
            LOGGER.info(String.format(FINISH_MESSAGE, sourceFile.getName(), usedMethod));
        }
    }

    public static void moveFileWithFileChannel(String sourceFilePath, String targetDir) {
        LOGGER.info(String.format(START_MESSAGE, sourceFilePath, targetDir));

        createDirectory(sourceFilePath);

        File sourceFile = new File(sourceFilePath);
        try (
                FileInputStream inputStream = new FileInputStream(sourceFilePath);
                FileOutputStream outputStream = new FileOutputStream(targetDir + File.separator + sourceFile.getName());
                FileChannel inChannel = inputStream.getChannel();
                FileChannel outChannel = outputStream.getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            int content = inChannel.read(buffer);
            while (content != -1) {
                content = inChannel.read(buffer);
            }
            buffer.flip();

            while (buffer.hasRemaining()) {
                outChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sourceFile.exists()) {
            sourceFile.delete();
            String usedMethod = "File Channel";
            LOGGER.info(String.format(FINISH_MESSAGE, sourceFile.getName(), usedMethod));
        }
    }

    public static void moveFileWithNIO2(String sourceFilePath, String targetDir) {
        LOGGER.info(String.format(START_MESSAGE, sourceFilePath, targetDir));

        createDirectory(sourceFilePath);
        File sourceFile = new File(sourceFilePath);

        Path source = Paths.get(sourceFilePath);
        Path target = Paths.get(targetDir + File.separator + sourceFile.getName());

        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

            String usedMethod = "NIO 2";
            LOGGER.info(String.format(FINISH_MESSAGE, sourceFile.getName(), usedMethod));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String dir, String fileName) {
        File directory = new File(dir);

        if (!directory.exists()) {
            directory.mkdir();
            LOGGER.info(String.format(MAKE_DIRECTORY_MESSAGE, directory));
        }

        File file = new File(dir + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();

                String fileContent = "File content";
                FileUtils.writeStringToFile(file, fileContent, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createDirectory(String path) {
        File targetDirectory = new File(path);

        if (!targetDirectory.exists()) {
            targetDirectory.mkdir();
            LOGGER.info(String.format(MAKE_DIRECTORY_MESSAGE, targetDirectory));
        }
    }
}
