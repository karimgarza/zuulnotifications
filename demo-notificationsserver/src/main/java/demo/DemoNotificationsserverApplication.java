package demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.util.FileSystemUtils;

@EnableJms
@SpringBootApplication
public class DemoNotificationsserverApplication {

    public static void main(String[] args) {
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        SpringApplication.run(DemoNotificationsserverApplication.class, args);
    }
}
