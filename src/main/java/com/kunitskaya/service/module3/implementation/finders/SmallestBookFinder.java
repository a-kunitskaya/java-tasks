package com.kunitskaya.service.module3.implementation.finders;

import com.kunitskaya.domain.module3.library.Book;
import com.kunitskaya.service.module3.StreamObjectFinder;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class SmallestBookFinder implements StreamObjectFinder<Book> {
    private static final String FOUND_BOOK_MESSAGE = "Book: %s, pages number: %s";
    private static final String SMALLEST_BOOK_MESSAGE = "Smallest book: %s, pages number: %s";
    private static final String NOT_FOUND_MESSAGE = "No smallest book is found";

    @Override
    public Book find(Stream<Book> books) {
        Optional<Book> book = books.peek(b -> LOGGER.info(String.format(FOUND_BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())))
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
