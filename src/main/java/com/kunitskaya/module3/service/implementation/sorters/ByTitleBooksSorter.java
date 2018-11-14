package com.kunitskaya.module3.service.implementation.sorters;

import com.kunitskaya.module3.domain.library.Book;
import com.kunitskaya.module3.service.StreamSorter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByTitleBooksSorter implements StreamSorter<Book> {

    private static final String BOOKS_MESSAGE = "Books in %s by title list:";
    private static final String BOOK_MESSAGE = "Book title: %s";

    @Override
    public void sort(Stream<Book> books) {
        String order = "unsorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));

        List<Book> sortedBooks = books.peek(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle())))
                                      .sorted(Comparator.comparing(Book::getTitle))
                                      .collect(Collectors.toList());

        order = "sorted";
        LOGGER.info(String.format(BOOKS_MESSAGE, order));
        sortedBooks.forEach(b -> LOGGER.info(String.format(BOOK_MESSAGE, b.getTitle())));
    }
}
