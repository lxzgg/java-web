package examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultServiceLocator {

    @Autowired
    private ClientService service;

    public static DefaultServiceLocator createStatic() {
        return new DefaultServiceLocator();
    }

    public DefaultServiceLocator createNew() {
        return new DefaultServiceLocator();
    }

    public DefaultServiceLocator createNew1() {
        return new DefaultServiceLocator();
    }

    public String abc() {
        System.out.println("调用方法");
        return "666";
    }
}
