package hello;

import com.amazonaws.util.EC2MetadataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String ip = "internal-internal-time-sb-lb-397912441.eu-west-1.elb.amazonaws.com";
    private String port = "8080";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String home() {

        RestTemplate restTemplate = new RestTemplate();
        Time time = null;
        String timeString = null;
        try{
          time=restTemplate.getForObject("http://" + ip + ":" + port + "/time", Time.class);

            timeString = time.getTime();
        }catch(Exception e){
            timeString = "could not get time";
        }
        log.info(timeString);


        return "Hello Docker World.  The time is " + timeString + ". Machine id is: " + EC2MetadataUtils.getInstanceId();
    }

}
