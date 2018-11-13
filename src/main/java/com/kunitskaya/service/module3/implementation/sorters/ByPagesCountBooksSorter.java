package com.kunitskaya.service.module3.implementation.sorters;

import com.kunitskaya.domain.module3.library.Book;
import com.kunitskaya.service.module3.StreamSorter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByPagesCountBooksSorter implements StreamSorter<Book> {
    private static final String BOOKS_MESSAGE = "Books in %s by pages count list:";
    private static final String BOOK_MESSAGE = "Book: %s,pages count: %s";

    public void sort(Stream<Book> books) {
        String order = "unsorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));

        List<Book> collect = books.peek(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())))
                                  .sorted(Comparator.comparingInt(Book::getNumberOfPages))
                                  .collect(Collectors.toList());

        order = "sorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));
        collect.forEach(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())));
    }
}

