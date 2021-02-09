package mk.ukim.finki.wpproekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WpProektApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpProektApplication.class, args);
    }

}
