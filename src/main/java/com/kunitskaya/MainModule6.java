package com.kunitskaya;

import com.kunitskaya.domain.module6.abstractbeans.A;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.kunitskaya.logging.ProjectLogger.*;

public class MainModule6 {

    public static void main(String[] args) {

        //Task 1. (30 points) Abstract beans with strange ties

        //1. Create bean A, use DI via setters, use property placeholder for values
        ApplicationContext appContextNoAutowiring = new ClassPathXmlApplicationContext("module6/task1_beans.xml");

        A a = appContextNoAutowiring.getBean("a", A.class);
        LOGGER.info("Setting name with property placeholder: " + a.getName());

        //2. Create bean B, use DI via constructor (bean A as argument of constructor)


        //3. Create bean C with singleton scope and bean D with prototype scope.
        // Add bean D as property of bean C.
        // Pay attention that they have different scopes. Consider Lookup Method Injection.

        //4. Create bean E and replace logic of one of its method by Method Replacement

        //5. Create bean F and log all possible steps from its lifecycle (lifecycle of Spring bean).
    }
}
