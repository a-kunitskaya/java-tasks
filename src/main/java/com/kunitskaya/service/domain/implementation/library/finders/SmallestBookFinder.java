package com.kunitskaya.service.domain.implementation.library.finders;

import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.ObjectFinder;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class SmallestBookFinder implements ObjectFinder<Book> {
    private static final String FOUND_BOOK_MESSAGE = "Book: %s, pages number: %s";
    private static final String SMALLEST_BOOK_MESSAGE = "Smallest book: %s, pages number: %s";
    private static final String NOT_FOUND_MESSAGE = "No smallest book is found";

    @Override
    public Book find(List<Book> books) {
        Optional<Book> book = books.stream()
                                   .peek(b -> LOGGER.info(String.format(FOUND_BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())))
                                   .min(Comparator.comparingInt(Book::getNumberOfPages));

        if (book.isPresent()) {
            Book smallestBook = book.get();

            LOGGER.info(String.format(SMALLEST_BOOK_MESSAGE, smallestBook.getTitle(), smallestBook.getNumberOfPages()));

            return smallestBook;
        } else {
            LOGGER.error(String.format(NOT_FOUND_MESSAGE, StringUtils.EMPTY));
            return null;
        }
    }
}
