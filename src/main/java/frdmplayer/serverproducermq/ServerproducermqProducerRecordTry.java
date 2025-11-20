package frdmplayer.serverproducermq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "frdmplayer")
@EnableJpaRepositories(basePackages = "frdmplayer.Repository")
@EntityScan(basePackages = "frdmplayer.Entity")


public class ServerproducermqProducerRecordTry {

    public static void main(String[] args) {
        SpringApplication.run(ServerproducermqProducerRecordTry.class, args);
    }

}
