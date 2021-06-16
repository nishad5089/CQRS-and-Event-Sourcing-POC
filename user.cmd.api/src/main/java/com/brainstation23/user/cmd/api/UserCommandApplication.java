package com.brainstation23.user.cmd.api;

import com.brainstation23.user.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class UserCommandApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserCommandApplication.class, args);
  }
}
