package uz.alex.photoappdiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PhotoAppDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppDiscoveryServerApplication.class, args);
    }

}
