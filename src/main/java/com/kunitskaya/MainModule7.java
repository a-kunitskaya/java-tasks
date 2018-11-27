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
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;


public class MainModule7 {

    public static void main(String[] args) throws IOException {
        //Task 1. (10 баллов) Сериализация
        //Добавьте для иерархии объектов из домашнего задания модуля Java Core I
        //возможность сериализоваться/десериализоваться.
        //Однако все численные поля не должны подвергаться этой процедуре.
        //Предоставьте тестовый код, в котором вы создаете несколько экземпляров различных
        //классов этой иерархии и успешно выполняете операцию сериализации/десереализации.
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
        //Напишите несколько версий утилиты FastFileMover,
        //которая перемещает файл из одной директории в другую директорию.
        //На вход принимает оба пути.
        //Все исключительные ситуации должны обрабатываться корректно.

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
