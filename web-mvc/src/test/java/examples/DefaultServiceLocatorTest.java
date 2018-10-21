package examples;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class DefaultServiceLocatorTest {

    private DefaultServiceLocator serviceLocator;

    @Test
    void a() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        serviceLocator = context.getBean("defaultServiceLocator", DefaultServiceLocator.class);
        serviceLocator.abc();
    }

}