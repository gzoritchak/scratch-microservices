package my.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class MyApp {

    public static void main(String args[]) throws Throwable {
        SpringApplication.run(MyApp.class, args);
    }
}

@RestController
class MyServiceRestController {

    @Autowired GreeterWithFallback greeterWithFallback;

    @RequestMapping("/")
    String home() {
        String who = "world";
        return "Calling greeter with :: " + who + "<br>"
                + greeterWithFallback.greet(who);
    }

}

@Component
class GreeterWithFallback {

    @HystrixCommand(fallbackMethod = "fallbackGreet")
    public String greet(String who) {
        return new RestTemplate()
                .getForEntity("http://localhost:8081/greeter/greet/" + who,
                        String.class).getBody();
    }

    public String fallbackGreet(String who){
        return who;
    }

}