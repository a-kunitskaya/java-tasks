package com.kunitskaya.service.domain.implementation.library;

import com.kunitskaya.domain.library.Author;
import com.kunitskaya.domain.library.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class BookPrinter {

    public static void printTitles(List<Book> books) {
        LOGGER.info("Getting books' titles...");

        books.stream()
             .map(Book::getTitle)
             .forEach(t -> LOGGER.info("Book's title: " + t));
    }

    public static void printDistinctAuthors(List<Book> books) {
        LOGGER.info("Getting all authors...");

        List<String> names = new ArrayList<>();

        for (Book book : books) {
            List<String> authors = book.getAuthors().stream()
                                       .map(Author::getName)
                                       .peek(n -> LOGGER.info("Found author's name: " + n))
                                       .collect(Collectors.toList());
            names.addAll(authors);
        }

        LOGGER.info("Printing distinct authors...");
        names.stream()
             .distinct()
             .forEach(n -> LOGGER.info("Distinct author's name: " + n));
    }
}
