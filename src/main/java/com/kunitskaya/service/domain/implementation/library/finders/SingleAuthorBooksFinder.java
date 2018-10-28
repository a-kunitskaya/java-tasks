package com.kunitskaya.service.domain.implementation.library.finders;

import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.ObjectsFinder;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class SingleAuthorBooksFinder implements ObjectsFinder<Book> {
    private static final String FINDING_MESSAGE = "Finding books with 1 author...";
    private static final String FOUND_BOOKS_MESSAGE = "Found %s books with 1 author.";
    private static final String FOUND_BOOK_MESSAGE = "Book: %s, author: %s";
    private static final String NOT_FOUND_MESSAGE = "No books with 1 author are found";

    @Override
    public List<Book> find(List<Book> books) {
        LOGGER.info(FINDING_MESSAGE);

        List<Book> booksWithOneAuthor = books.parallelStream()
                                             .filter(b -> b.getAuthors().size() == 1)
                                             .peek(b -> LOGGER.info(String.format(FOUND_BOOK_MESSAGE, b.getTitle(), b.getAuthors().get(0).getName())))
                                             .collect(Collectors.toList());

        if (booksWithOneAuthor.size() != 0) {
            LOGGER.info(String.format(FOUND_BOOKS_MESSAGE, booksWithOneAuthor.size()));
        } else {
            LOGGER.error(NOT_FOUND_MESSAGE);
        }
        return booksWithOneAuthor;
    }
}
