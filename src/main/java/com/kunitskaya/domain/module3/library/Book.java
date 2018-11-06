package com.kunitskaya.domain.module3.library;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class Book {
    String title;
    List<Author> authors;
    int numberOfPages;

    public Book(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public static void setAuthors(List<Book> booksList, List<Author> authorsList) {
        String peekMessage = "Setting book: %s, to author: %s";
        for (Book book : booksList) {
            List<Author> authors = authorsList.stream()
                                              .filter(a -> a.getBooks().contains(book))
                                              .peek(a -> LOGGER.info(String.format(peekMessage, book.getTitle(), a.getName())))
                                              .collect(Collectors.toList());
            book.setAuthors(authors);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, numberOfPages);
    }
}
