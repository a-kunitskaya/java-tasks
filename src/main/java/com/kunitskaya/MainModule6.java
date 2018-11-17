package com.kunitskaya;

import com.kunitskaya.module4.domain.Employee;
import com.kunitskaya.module4.domain.Position;
import com.kunitskaya.module4.domain.Salary;
import com.kunitskaya.module4.service.PositionService;
import com.kunitskaya.module4.service.config.AppContext;
import com.kunitskaya.module6.domain.abstractbeans.*;
import com.kunitskaya.module6.domain.salaryemulator.Skill;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MainModule6 {

    public static void main(String[] args) {

        //TODO: optimize imports, reformat code

        //Task 1.Abstract beans with strange ties
        //1.1. Create bean A, use DI via setters, use property placeholder for values
        ApplicationContext context = new ClassPathXmlApplicationContext("module6/tasks1-4_beans.xml");

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

        LOGGER.info("singleton1 and singleton2 are the same object: " + singleton1.equals(singleton2));
        LOGGER.info("prototype1 and prototype2 are different objects: " + prototype1.equals(prototype2));

        //1.4. Create bean E and replace logic of one of its method by Method Replacement
        E e = context.getBean("e", E.class);
        e.replacedMethod();

        //1.5. Create bean F and log all possible steps from its lifecycle (lifecycle of Spring bean).
        ApplicationContext contextLogging = new ClassPathXmlApplicationContext("module6/task1.5_beans.xml");

        F f = contextLogging.getBean("f", F.class);

        ((AbstractApplicationContext) contextLogging).close();

        //Task 2. Upgrade of Salary Emulator
        //2.1.	Use factory-method (singleton) and factory-bean (service locator)
        //2.9.	Use math constants in bean definition to calculate Salary with Math power
        //2.10.	Make custom Bean Postprocessor to mutate salary value (it happens)
        ApplicationContext context2 = new ClassPathXmlApplicationContext("module6/task2_beans.xml");

        Salary salaryFactoryMethod = context2.getBean("salary_factory_method", Salary.class);
        LOGGER.info("Created instance with factory-method: " + salaryFactoryMethod.toString());

        Salary salaryFactoryBean = context2.getBean("salary_factory_bean", Salary.class);
        LOGGER.info("Created instance with factory-bean: " + salaryFactoryBean.toString());

        //2.2.	Use FactoryBean with ConfigurationSupport or implement FactoryBean interface
        Salary salaryFactoryBeanInterface = context2.getBean("salary", Salary.class);
        LOGGER.info("Created salary by implementing FactoryBean interface: " + salaryFactoryBeanInterface.toString());

        //2.3.	Divide complex Java configuration into a few simple Java configs
        //2.5.	Migrate appropriate beans to prototype scope
        ApplicationContext appContextAnnotations = new AnnotationConfigApplicationContext(AppContext.class);
        Employee prototypeEmployee1 = appContextAnnotations.getBean(Employee.class);
        Employee prototypeEmployee2 = appContextAnnotations.getBean(Employee.class);
        LOGGER.info("Java-based configuration with annotations: " + prototypeEmployee2.toString());

        prototypeEmployee1.setName("John");
        prototypeEmployee2.setName("Jack");

        boolean areEmployeesSame = prototypeEmployee1.getName().equals(prototypeEmployee2.getName());
        LOGGER.info("prototypeEmployee1 and prototypeEmployee2 are different objects: " + !areEmployeesSame);

        //2.6.	Add a new entity Skill, one Position can require a few skills
        //2.7.	Inject list of skills to appropriate beans
        Position javaDevPosition = context2.getBean("java_dev_position", Position.class);

        PositionService.readRequiredSkills(javaDevPosition);

        //2.6. ...and the final salary can depends on skill rating (like TIOBE Programming Language Rating)
        PositionService.recalculateSalaryForPosition(javaDevPosition);

        //2.8.	Implement a method that can be called when the skill become unpopular and company drops
        // it from the list of skills required to any position
        Skill springSkill = javaDevPosition.getSkills().get(1);
        PositionService.dropSkill(javaDevPosition, springSkill);

        PositionService.readRequiredSkills(javaDevPosition);

        //2.4.	Implement bean that sends message to log at initialization and destroy phases
        ((ClassPathXmlApplicationContext) context2).close();
    }
}
