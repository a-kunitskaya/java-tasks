package com.kunitskaya;

import com.kunitskaya.module7.CustomFileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class MainModule7 {

    public static void main(String[] args) throws IOException {
        //Task 1. (10 баллов) Сериализация
        //Добавьте для иерархии объектов из домашнего задания модуля Java Core I
        //возможность сериализоваться/десериализоваться.
        //Однако все численные поля не должны подвергаться этой процедуре.
        //Предоставьте тестовый код, в котором вы создаете несколько экземпляров различных
        //классов этой иерархии и успешно выполняете операцию сериализации/десереализации.


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
