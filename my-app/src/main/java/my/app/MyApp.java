package my.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
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

    @Autowired GreeterClient greeterClient;

    @HystrixCommand(fallbackMethod = "fallbackGreet")
    public String greet(String who) {
        return greeterClient.greet(who);
    }

    public String fallbackGreet(String who){
        return who;
    }
}

@FeignClient("my-service")
interface GreeterClient {

    @RequestMapping(method = RequestMethod.GET, value = "/greeter/greet/{who}")
    String  greet(@PathVariable("who") String who);
}
