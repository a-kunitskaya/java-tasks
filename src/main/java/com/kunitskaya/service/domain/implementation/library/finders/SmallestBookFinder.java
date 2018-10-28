package com.kunitskaya.service.domain.implementation.library.finders;

import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.ObjectFinder;

import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class SmallestBookFinder implements ObjectFinder<Book> {

    @Override
    public Book find(List<Book> books) {
        int minNumberOfPages = books.get(0).getNumberOfPages();
        Book smallestBook = books.get(0);

        for (Book b : books) {
            if (b.getNumberOfPages() < minNumberOfPages) {
                smallestBook = b;
                minNumberOfPages = b.getNumberOfPages();
            }
        }

        LOGGER.info("Book with min number of pages: " + smallestBook.getTitle());

        return smallestBook;
    }
}
