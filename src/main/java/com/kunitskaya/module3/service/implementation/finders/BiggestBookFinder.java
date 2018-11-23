package com.kunitskaya.module3.service.implementation.finders;

import com.kunitskaya.module3.domain.library.Author;
import com.kunitskaya.module3.domain.library.Book;
import com.kunitskaya.module3.service.StreamObjectFinder;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class BiggestBookFinder implements StreamObjectFinder<Book> {
    private static final String FOUND_BOOK_MESSAGE = "Book: %s, pages number: %s";
    private static final String BIGGEST_BOOK_MESSAGE = "Biggest book: %s, pages number: %s";
    private static final String NOT_FOUND_MESSAGE = "No biggest book is found by author: %s";

    @Override
    public Book find(Stream<Book> books) {
        Optional<Book> book = books.peek(b -> LOGGER.info(String.format(FOUND_BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())))
                                   .max(Comparator.comparingInt(Book::getNumberOfPages));

        if (book.isPresent()) {
            Book biggestBook = book.get();

            LOGGER.info(String.format(BIGGEST_BOOK_MESSAGE, biggestBook.getTitle(), biggestBook.getNumberOfPages()));

            return biggestBook;
        } else {
            LOGGER.error(String.format(NOT_FOUND_MESSAGE, StringUtils.EMPTY));
            return null;
        }
    }

    public Book find(Author author) {
        String authorName = author.getName();
        LOGGER.info("Finding biggest book by author: " + authorName);

        Optional<Book> book = author.getBooks().stream()
                                    .peek(b -> LOGGER.info(String.format(FOUND_BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())))
                                    .sorted(Comparator.comparingInt(Book::getNumberOfPages).reversed())
                                    .findFirst();
        if (book.isPresent()) {
            Book biggestBook = book.get();

            LOGGER.info(String.format(BIGGEST_BOOK_MESSAGE, biggestBook.getTitle(), biggestBook.getNumberOfPages()));

            return biggestBook;
        } else {
            LOGGER.error(String.format(NOT_FOUND_MESSAGE, authorName));
            return null;
        }
    }
}
