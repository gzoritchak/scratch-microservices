package my.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyApp {

    public static void main(String args[]) throws Throwable {
        SpringApplication.run(MyApp.class, args);
    }
}

@RestController
class MyServiceRestController {
    @RequestMapping("/")
    String home() {
        String who = "world";
        String content = "Calling greeter with :: " + who;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8081/greeter/greet/" + who, String.class);
        return content + "<br>" + forEntity.getBody();
    }
}
