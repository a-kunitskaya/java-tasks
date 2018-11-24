package com.kunitskaya;

import com.kunitskaya.module7.CustomFileUtils;

import java.io.IOException;
import java.nio.file.Paths;

public class MainModule7 {

    public static void main(String[] args) throws IOException {
        //Task 1. (10 баллов) Сериализация
        //Добавьте для иерархии объектов из домашнего задания модуля Java Core I возможность сериализоваться/десериализоваться. Однако все численные поля не должны подвергаться этой процедуре.
        //Предоставьте тестовый код, в котором вы создаете несколько экземпляров различных классов этой иерархии и успешно выполняете операцию сериализации/десереализации.

        //Task 2. (20 баллов) DiskAnalyzer
        //Напишите утилиту DiskAnalyzer командной строки, которая на вход принимает путь (например C:\) и номер функции, корректно обрабатывает некорректные пути или номера функций. Утилита выводит в файл результаты своей работы. Программа должна работать для диска C: вашей рабочей машины. Поддерживаются следующие функции:
        //1.	(5 баллов) Поиск имени файла с максимальным количеством букв ‘s’ в имени, вывод пути к нему.
        //2.	(5 баллов) Top-5 файлов с самым большим размером
        //3.	(5 баллов) Средний размер файла в указанной директории или любой ее поддиректории
        //4.	(5 баллов) Количество файлов и папок, разбитое по первым буквам алфавита (например, на букву A – начинается 100 000 файлов и 200 папок)

        //Task 3. (30 баллов) FastFileMover
        //Напишите несколько версий утилиты FastFileMover, которая перемещает файл из одной директории в другую директорию. На вход принимает оба пути. Все исключительные ситуации должны обрабатываться корректно.

        //1.	(5 баллов) Версия использует простые FileStreams
        String pathFrom = Paths.get("src", "main", "resources", "module7", "source", "file.txt").toString();
        String pathTo = Paths.get("src", "main", "resources", "module7", "target").toString();

        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithFileStream(pathFrom, pathTo);

        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithBufferedStream100(pathFrom, pathTo);

        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithFileChannel(pathFrom, pathTo);

        CustomFileUtils.createFile(pathFrom);
        CustomFileUtils.moveFileWithNIO(pathFrom, pathTo);

        //2.	(5 баллов) Версия использует FileStreams с буфером в 100 Кб
        //3.	(5 баллов) Версия использует FileChannel
        //https://stackoverflow.com/questions/1146153/copying-files-from-one-directory-to-another-in-java


        //4.	(5 баллов) Версия использует NIO 2 File API
        //Замерьте время на копирование, запустите на нескольких эталонных файлах разного размера (1 Кб, 100 Кб, 10 Мб, 1 Гб). На каждом файле запустите 1000 раз, получите среднее время.
        //(10 баллов) Подготовьте performance report в разрезе размер файла/способ.
    }
}
