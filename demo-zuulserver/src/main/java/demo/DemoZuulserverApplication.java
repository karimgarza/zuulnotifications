package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class DemoZuulserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoZuulserverApplication.class, args);
    }
}
