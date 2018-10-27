package com.kunitskaya.service.domain.implementation.library;

import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.ObjectFinder;

import java.util.List;

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

        return biggestBook;
    }
}
