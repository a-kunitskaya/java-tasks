package com.kunitskaya.service.domain.implementation.library.sorters;

import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.Sorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByTitleBooksSorter implements Sorter<Book> {

    private static final String BOOKS_MESSAGE = "Books in %s by title list:";
    private static final String BOOK_MESSAGE = "Book title: %s";

    @Override
    public void sort(List<Book> books) {
        String order = "unsorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));
        books.forEach(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle())));

        books.sort(Comparator.comparing(Book::getTitle));

        order = "sorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));
        books.forEach(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle())));
    }
}
