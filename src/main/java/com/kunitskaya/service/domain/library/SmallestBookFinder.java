package com.kunitskaya.service.domain.library;

import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.ObjectFinder;

import java.util.List;

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

        return smallestBook;
    }
}
