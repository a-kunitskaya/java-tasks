package com.kunitskaya.service.module3.implementation.sorters;

import com.kunitskaya.domain.module3.library.Book;
import com.kunitskaya.service.module3.Sorter;

import java.util.Comparator;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByPagesNumberBooksSorter implements Sorter<Book> {
    private static final String BOOKS_MESSAGE = "Books in %s by number of pages list:";
    private static final String BOOK_MESSAGE = "Book: %s, number of pages: %s";

    @Override
    public void sort(List<Book> books) {
        String order = "unsorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));
        books.forEach(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())));

        books.sort(Comparator.comparingInt(Book::getNumberOfPages));

        order = "sorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));
        books.forEach(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())));
    }
}

