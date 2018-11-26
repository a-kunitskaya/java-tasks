package com.kunitskaya;

import com.kunitskaya.module7.CustomFileUtils;

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
        String pathFrom = Paths.get("src", "main", "resources", "module7", "source", "file.txt").toString();
        String pathTo = Paths.get("src", "main", "resources", "module7", "target").toString();

        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithFileStream(pathFrom, pathTo);

        //3.2. Версия использует FileStreams с буфером в 100 Кб
        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithBufferedStream100(pathFrom, pathTo);

        //3.3. Версия использует FileChannel
        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithFileChannel(pathFrom, pathTo);

        //3.4. Версия использует NIO 2 File API
        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithNIO2(pathFrom, pathTo);

    }
}
