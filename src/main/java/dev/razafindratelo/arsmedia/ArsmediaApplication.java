package dev.razafindratelo.arsmedia;

import dev.razafindratelo.arsmedia.InfraGenerated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@InfraGenerated
@SpringBootApplication
@EnableScheduling
public class ArsmediaApplication {

  public static void main(String[] args) {
    SpringApplication.run(ArsmediaApplication.class, args);
  }
}
