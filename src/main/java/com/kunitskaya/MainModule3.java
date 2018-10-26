package com.kunitskaya;

import com.kunitskaya.domain.library.Author;
import com.kunitskaya.domain.library.Book;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainModule3 {

    public static void main(String[] args) {
        short age1 = 32;
        short age2 = 44;

        Author author1 = new Author("Jack", age1);
        Author author2 = new Author("Tom", age2);
        Author author3 = new Author("Bill", age1);

        List<Author> authorsList = Arrays.asList(author1, author2, author3);
        
        Book book1 = new Book("Cat in the hat", 50);
        Book book2 = new Book("The wizard of OZ", 150);
        Book book3 = new Book("Winnie the Pooh", 30);
        Book book4 = new Book("Click clack moo", 24);
        Book book5 = new Book("About a dog and a cat", 56);

        List<Book> booksList = Arrays.asList(book1, book2, book3, book4, book5);

        book1.setAuthors(Collections.singletonList(author1));
        book2.setAuthors(Collections.singletonList(author1));
        book3.setAuthors(Collections.singletonList(author2));
        book3.setAuthors(Arrays.asList(author1, author3));
        book4.setAuthors(Arrays.asList(author1, author2));
        book5.setAuthors(Arrays.asList(author1, author2));

        author1.setBooks(booksList);
        author2.setBooks(Arrays.asList(book3, book3, book5));
        author3.setBooks(Collections.singletonList(book3));

        Author[] authors = authorsList.toArray(new Author[0]);
        Book[] books = booksList.toArray(new Book[0]);
    }
}
