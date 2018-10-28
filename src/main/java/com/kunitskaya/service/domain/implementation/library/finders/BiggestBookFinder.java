package com.kunitskaya.service.domain.implementation.library.finders;

import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.ObjectFinder;

import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class BiggestBookFinder implements ObjectFinder<Book> {

    @Override
    public Book find(List<Book> books) {
        int maxNumberOfPages = books.get(0).getNumberOfPages();
        Book biggestBook = books.get(0);

        for (Book b : books) {
            if (b.getNumberOfPages() > maxNumberOfPages) {
                biggestBook = b;
                maxNumberOfPages = biggestBook.getNumberOfPages();
            }
        }

        LOGGER.info("Book with max number of pages: " + biggestBook.getTitle());

        return biggestBook;
    }
}
