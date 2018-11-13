package com.kunitskaya;

import com.google.common.collect.ImmutableList;
import com.kunitskaya.domain.module3.collectors.A;
import com.kunitskaya.domain.module3.library.Author;
import com.kunitskaya.domain.module3.library.Book;
import com.kunitskaya.service.module3.implementation.BookPrinter;
import com.kunitskaya.service.module3.implementation.finders.BiggestBookFinder;
import com.kunitskaya.service.module3.implementation.finders.ByPagesCountBookFinder;
import com.kunitskaya.service.module3.implementation.finders.SingleAuthorBooksFinder;
import com.kunitskaya.service.module3.implementation.finders.SmallestBookFinder;
import com.kunitskaya.service.module3.implementation.sorters.ByPagesCountBooksSorter;
import com.kunitskaya.service.module3.implementation.sorters.ByTitleBooksSorter;
import com.kunitskaya.service.module3.interfaces.ThreeFunction;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;
import static com.kunitskaya.service.module3.collectors.CustomImmutableListCollector.toCustomImmutablelist;

public class MainModule3 {
    private static final String MESSAGE = "Functional interface: %s, result: %s";

    public static void main(String[] args) {

        //task 4
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

        List<Book> booksList = Arrays.asList(book1, book2, book3, book4, book5);

        author1.setBooks(booksList);
        author2.setBooks(Arrays.asList(book4, book5));
        author3.setBooks(Collections.singletonList(book3));

        Book.setAuthors(booksList, authorsList);

        Author[] authors = authorsList.toArray(new Author[0]);
        Book[] books = booksList.toArray(new Book[0]);


        //task 4.3
        //I.	check if some/all book(s) have more than 200 pages;
        List<Book> booksMoreThan200Pages = new ByPagesCountBookFinder(200).find(booksList.stream());
        List<Book> booksMoreThan20PagesParallel = new ByPagesCountBookFinder(200).find(booksList.parallelStream());

        //II.	find the books with max and min number of pages;
        Book smallestBook = new SmallestBookFinder().find(Arrays.stream(books));
        Book smallestBookParallel = new SmallestBookFinder().find(Arrays.stream(books).parallel());

        Book biggestBook = new BiggestBookFinder().find(booksList.stream());
        Book biggestBookParallel = new BiggestBookFinder().find(booksList.parallelStream());

        //III.	filter books with only single author;
        List<Book> booksWithOneAuthor = new SingleAuthorBooksFinder().find(booksList.stream());
        List<Book> booksWithOneAuthorParallel = new SingleAuthorBooksFinder().find(Stream.of(books).parallel());

        //IV.	sort the books by number of pages/title;
        new ByPagesCountBooksSorter().sort(booksList.stream());
        new ByPagesCountBooksSorter().sort(booksList.parallelStream());

        new ByTitleBooksSorter().sort(booksList.stream());
        new ByTitleBooksSorter().sort(booksList.parallelStream());

        //V.	get list of all titles;
        // VI.	print them using forEach method;
        BookPrinter.printTitles(booksList.stream());
        BookPrinter.printTitles(booksList.parallelStream());

        //VII.	get distinct list of all authors
        BookPrinter.printDistinctAuthors(booksList.stream());
        BookPrinter.printDistinctAuthors(booksList.parallelStream());

        Book biggestAuthorsBook = new BiggestBookFinder().find(author1);

        //task 5
        List<A> aInstances = A.getAInstances(10);
        ImmutableList<Integer> immutableList = aInstances.stream()
                                                         .filter(A::hasEvenNumber)
                                                         .map(i -> i.addNumber(3))
                                                         .collect(toCustomImmutablelist());
        //task 2-3
        A instance1 = new A("instance1", 124, true);
        A instance2 = new A("instance2", 12, false);
        A instance3 = new A("instance3", 10, true);

        Predicate<A> isEvenNumber = a -> a.hasEvenNumber();
        LOGGER.info(String.format(MESSAGE, "Predicate", isEvenNumber.test(instance1)));

        Consumer<A> namePrinter = m -> LOGGER.info(String.format(MESSAGE, "Consumer", m.getName()));
        namePrinter.accept(instance1);

        Supplier<Integer> randomIntSupplier = () -> RandomUtils.nextInt();
        Integer supplierResult = randomIntSupplier.get();
        LOGGER.info(String.format(MESSAGE, "Supplier", +supplierResult));

        Function<A, Integer> functionResult = a -> a.addNumber(supplierResult);
        LOGGER.info(String.format(MESSAGE, "Function", functionResult.apply(instance1)));

        BiFunction<String, A, Boolean> isNameInString = (s, a) -> StringUtils.containsIgnoreCase(s, a.getName());
        String randomString = RandomStringUtils.randomAlphanumeric(5, 20);
        Boolean biFunctionResult = isNameInString.apply(randomString, instance1);
        LOGGER.info(String.format(MESSAGE, "BiFunction", String.valueOf(biFunctionResult)));

        ThreeFunction.printStartMessage(instance1.getName(), instance2.getName(), instance3.getName());
        ThreeFunction<A, A, A, Integer> threeFunction = (x, y, z) -> x.getNumber() + y.getNumber() + z.getNumber();
        ThreeFunction<A, A, A, Integer> then = threeFunction.andThen(r -> r * 5);
        int threeFunctionResult = then.apply(instance1, instance2, instance3);
        LOGGER.info(String.format(MESSAGE, "ThreeFunction", threeFunctionResult));

        ThreeFunction<A, A, A, Integer> anonymousThen = then.andThen(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer r) {
                return r * 2;
            }
        });

        Integer anonymousResult = anonymousThen.apply(instance1, instance2, instance3);
        LOGGER.info(String.format(MESSAGE, "ThreeFunction with anonymous class", anonymousResult));

        ThreeFunction<A, A, A, Integer> multiply = (x, y, z) -> anonymousThen.multiply(x.getNumber(), y.getNumber(), z.getNumber());
        Integer multiplyResult = multiply.apply(instance1, instance2, instance3);
        LOGGER.info(String.format(MESSAGE, "ThreeFunction result (another default method)", multiplyResult));

        ThreeFunction.printFinishMessage();
    }
}

