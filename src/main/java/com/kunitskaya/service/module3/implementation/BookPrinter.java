package com.kunitskaya.service.module3.implementation;

import com.kunitskaya.domain.module3.library.Author;
import com.kunitskaya.domain.module3.library.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class BookPrinter {

    public static void printTitles(Stream<Book> books) {
        LOGGER.info("Getting books' titles...");

        books.map(Book::getTitle)
             .forEach(t -> LOGGER.info("Book's title: " + t));
    }

    public static void printDistinctAuthors(Stream<Book> books) {
        LOGGER.info("Getting all authors...");

        List<String> names = new ArrayList<>();

        books.forEach(b ->
                names.addAll(b.getAuthors().stream()
                              .map(Author::getName)
                              .peek(n -> LOGGER.info("Found author's name: " + n))
                              .collect(Collectors.toList()))

        );

        LOGGER.info("Printing distinct authors...");
        names.stream()
             .distinct()
             .forEach(n -> LOGGER.info("Distinct author's name: " + n));
    }
}
