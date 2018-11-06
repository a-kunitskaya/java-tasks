package com.kunitskaya.service.module3.implementation.finders;

import com.kunitskaya.domain.module3.library.Book;
import com.kunitskaya.service.module3.StreamObjectsFinder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;


public class ByPagesBookFinder implements StreamObjectsFinder<Book> {
    private static final String FINDING_MESSAGE = "Finding books with pages number > %s...";
    private static final String FOUND_BOOKS_MESSAGE = "Found %s books with pages number > than %s.";
    private static final String FOUND_BOOK_MESSAGE = "Book with title: %s, pages number: %s";
    private static final String NOT_FOUND_MESSAGE = "No books are found with pages number: %s";


    private int minPagesNumber;


    public ByPagesBookFinder(int minPagesNumber) {
        this.minPagesNumber = minPagesNumber;
    }

    /**
     * Finds a book by min number of pages exclusive
     */
    @Override
    public List<Book> find(Stream<Book> books) {
        LOGGER.info(String.format(FINDING_MESSAGE, minPagesNumber));

        List<Book> booksWithPagesNumber = books.filter(b -> b.getNumberOfPages() > minPagesNumber)
                                               .peek(b -> LOGGER.info(String.format(FOUND_BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())))
                                               .collect(Collectors.toList());

        if (booksWithPagesNumber.size() != 0) {
            LOGGER.info(String.format(FOUND_BOOKS_MESSAGE, booksWithPagesNumber.size(), minPagesNumber));
        } else {
            LOGGER.error(String.format(NOT_FOUND_MESSAGE, minPagesNumber));
        }

        return booksWithPagesNumber;
    }
}
