package com.kunitskaya.service.domain.implementation.library;

import com.kunitskaya.domain.library.Author;
import com.kunitskaya.domain.library.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookPrinter {

    public static void printTitles(List<Book> books) {
        books.stream()
             .map(Book::getTitle)
             .forEach(System.out::println);
    }

    public static void printDistinctAuthors(List<Book> books) {

        List<String> names = new ArrayList<>();

        for (Book book : books) {
            List<String> authors = book.getAuthors().stream()
                                       .map(Author::getName)
                                       .collect(Collectors.toList());
            names.addAll(authors);
        }

        names.stream()
             .distinct()
             .forEach(System.out::println);
    }
}
