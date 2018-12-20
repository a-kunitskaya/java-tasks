package com.kunitskaya;

import com.kunitskaya.module1.HomeLocation;
import com.kunitskaya.module1.domain.Fridge;
import com.kunitskaya.module1.domain.HouseholdAppliance;
import com.kunitskaya.module1.domain.Kettle;
import com.kunitskaya.module1.domain.TvSet;
import com.kunitskaya.module2.reflection.MetaDataPrinter;
import com.kunitskaya.module7.CustomFileUtils;
import com.kunitskaya.module7.Serializer;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;


public class MainModule7 {

    public static void main(String[] args) {

        //Task 1. Сериализация
        String serDir = Paths.get("src", "main", "resources", "module7", "serializable").toString();
        String serFileName = File.separator + "household_appliance.ser";
        String serFilePath = serDir + serFileName;

        CustomFileUtils.createFile(serDir, serFileName);

        Fridge serializedFridge = new Fridge(250, true, "Fridge", HomeLocation.KITCHEN, 3);
        Kettle serializedKettle = new Kettle(250, true, "Fridge", HomeLocation.KITCHEN, 2.0);
        TvSet serializedTvSet = new TvSet(250, true, "Fridge", HomeLocation.KITCHEN, 30.0);

        List<HouseholdAppliance> serializedAppliances = Arrays.asList(serializedFridge, serializedKettle, serializedTvSet);
        Serializer serializer = new Serializer();

        serializedAppliances.forEach(a -> {
            serializer.serialize(serFilePath, a);
            HouseholdAppliance deserializedAppliance = serializer.deserialize(serFilePath, a.getClass());
            LOGGER.info(deserializedAppliance.toString());
            MetaDataPrinter.printTransientFields(a.getClass());
        });

        //Task 3. FastFileMover
        //3.1. Версия использует простые FileStreams
        String sourceDir = Paths.get("src", "main", "resources", "module7", "source").toString();
        String sourceFileName = File.separator + "file.txt";
        String sourceFilePath = sourceDir + sourceFileName;
        String targetDir = Paths.get("src", "main", "resources", "module7", "target").toString();

        CustomFileUtils.createFile(sourceDir, sourceFileName);
        CustomFileUtils.moveFileWithFileStream(sourceFilePath, targetDir);

        //3.2. Версия использует FileStreams с буфером в 100 Кб
        CustomFileUtils.createFile(sourceDir, sourceFileName);
        CustomFileUtils.moveFileWithBufferedStream100(sourceFilePath, targetDir);

        //3.3. Версия использует FileChannel
        CustomFileUtils.createFile(sourceDir, sourceFileName);
        CustomFileUtils.moveFileWithFileChannel(sourceFilePath, targetDir);

        //3.4. Версия использует NIO 2 File API
        CustomFileUtils.createFile(sourceDir, sourceFileName);
        CustomFileUtils.moveFileWithNIO2(sourceFilePath, targetDir);
    }
}
