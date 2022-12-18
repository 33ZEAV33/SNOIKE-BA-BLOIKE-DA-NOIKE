package com.donkey.snoike.application;

import com.donkey.snoike.view.GameFrame;
import com.donkey.snoike.view.GamePanel;
import java.awt.Graphics2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public void draw(Graphics2D g) {
  }
  @Override
  public void run(String... strings) throws Exception {
    GamePanel gamePanel = new GamePanel(this::draw);
    GameFrame gameFrame = new GameFrame(gamePanel);
    log.info("setting frame visibility");
    gameFrame.setVisible(true);
//    java.awt.EventQueue.invokeLater(() -> {
//      log.info("setting frame visibility");
//      gameFrame.setVisible(true);
//    });
  }
}
