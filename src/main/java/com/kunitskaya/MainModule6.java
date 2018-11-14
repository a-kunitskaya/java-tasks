package com.kunitskaya;

import com.kunitskaya.domain.module6.abstractbeans.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MainModule6 {

    public static void main(String[] args) {

        //1.1. Create bean A, use DI via setters, use property placeholder for values
        ApplicationContext context = new ClassPathXmlApplicationContext("module6/task1_beans.xml");

        A a = context.getBean("a", A.class);
        LOGGER.info("Setting name with property placeholder: " + a.getName());

        //1.2. Create bean B, use DI via constructor (bean A as argument of constructor)
        B b = context.getBean("b", B.class);
        LOGGER.info("Bean A is passed as a constructor argument to B: " + b.getA().toString());

        //1.3. Create bean C with singleton scope and bean D with prototype scope.
        // Add bean D as property of bean C. - Lookup Method Injection.
        C singleton1 = context.getBean("c", C.class);
        D prototype1 = singleton1.getD();

        C singleton2 = context.getBean("c", C.class);
        D prototype2 = singleton2.getD();

        LOGGER.info("singleton1 and singleton2 are the same object: " + String.valueOf(singleton1 == singleton2));
        LOGGER.info("prototype1 and prototype2 are different objects: " + String.valueOf(prototype1 != prototype2));

        //1.4. Create bean E and replace logic of one of its method by Method Replacement
        E e = context.getBean("e", E.class);
        e.replacedMethod();

        //1.5. Create bean F and log all possible steps from its lifecycle (lifecycle of Spring bean).
    }
}
