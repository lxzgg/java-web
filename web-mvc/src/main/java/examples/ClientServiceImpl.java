package examples;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ClientServiceImpl implements ClientService {

    @Override
    public void a() {
        System.out.println("haha");
    }
}
