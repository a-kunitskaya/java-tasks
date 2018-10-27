package com.kunitskaya;

import com.kunitskaya.domain.library.Author;
import com.kunitskaya.domain.library.Book;
import com.kunitskaya.service.domain.library.BiggestBookFinder;
import com.kunitskaya.service.domain.library.SmallestBookFinder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MainModule3 {
    private static String message;

    public static void main(String[] args) {
        short age1 = 32;
        short age2 = 44;

        Author author1 = new Author("Jack", age1);
        Author author2 = new Author("Tom", age2);
        Author author3 = new Author("Bill", age1);

        List<Author> authorsList = Arrays.asList(author1, author2, author3);

        Book book1 = new Book("Cat in the hat", 50);
        Book book2 = new Book("The wizard of OZ", 250);
        Book book3 = new Book("Winnie the Pooh", 230);
        Book book4 = new Book("Click Clack Moo", 24);
        Book book5 = new Book("About a dog and a cat", 156);

        List<Book> booksList = Arrays.asList(book2, book3, book4, book5);

        author1.setBooks(booksList);
        author2.setBooks(Arrays.asList(book1, book2, book3, book5));
        author3.setBooks(Collections.singletonList(book3));

        //throws StackOverflowError exception.
        // Cannot evaluate com.kunitskaya.domain.library.Author.toString()


        book1.setAuthors(Collections.singletonList(author1));
        book2.setAuthors(Collections.singletonList(author1));
        book3.setAuthors(Collections.singletonList(author2));
        book3.setAuthors(Arrays.asList(author1, author3));
        book4.setAuthors(Arrays.asList(author1, author2));
        book5.setAuthors(Arrays.asList(author1, author2));



        for (Author a : authorsList){
            System.out.println(a.getName() + a.getBooks());
        }
        Author[] authors = authorsList.toArray(new Author[0]);
        Book[] books = booksList.toArray(new Book[0]);

        List<Book> booksMoreThan200pages = booksList.stream()
                                                    .filter(b -> b.getNumberOfPages() > 200)
                                                    .collect(Collectors.toList());


        Book smallestBook = new SmallestBookFinder().find(booksList);
        LOGGER.info("Book with min number of pages: " + smallestBook.getTitle());

        Book biggestBook = new BiggestBookFinder().find(booksList);
        LOGGER.info("Book with max number of pages: " + biggestBook.getTitle());


        message = "Book with title: %s has > 200 pages: %s";
        booksMoreThan200pages.forEach(b -> {
            LOGGER.info(String.format(message, b.getTitle(), b.getNumberOfPages()));
        });
    }
}
