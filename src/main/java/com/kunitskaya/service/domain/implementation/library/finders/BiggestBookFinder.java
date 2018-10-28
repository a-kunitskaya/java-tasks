package com.kunitskaya.service.domain.implementation.library.finders;

import com.kunitskaya.domain.library.Author;
import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.ObjectFinder;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class BiggestBookFinder implements ObjectFinder<Book> {
    private static final String FOUND_BOOK_MESSAGE = "Book: %s, pages number: %s";
    private static final String BIGGEST_BOOK_MESSAGE = "Biggest book: %s, pages number: %s";
    private static final String NOT_FOUND_MESSAGE = "No biggest book is found by author: %s";


    @Override
    public Book find(List<Book> books) {

        Optional<Book> book = books.stream()
                                   .peek(b -> LOGGER.info(String.format(FOUND_BOOK_MESSAGE, b.getTitle(), b.getNumberOfPages())))
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
