package my.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MyServiceApp {

    public static void main(String args[]) throws Throwable {
        SpringApplication.run(MyServiceApp.class, args);
    }
}

@RestController
class MyServiceRestController {
    @RequestMapping("/greeter/greet/{who}")
    String greet(@PathVariable String who) {
        return "Hello " + who;
    }
}


