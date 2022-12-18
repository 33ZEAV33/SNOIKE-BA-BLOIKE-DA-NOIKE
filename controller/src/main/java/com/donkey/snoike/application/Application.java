package com.donkey.snoike.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class Application {
  private static final Logger log = LoggerFactory.getLogger("Application");
  public static void main(String[] args) {
    log.info("starting application");
    new SpringApplicationBuilder(Application.class).headless(false).run(args);
  }
}
