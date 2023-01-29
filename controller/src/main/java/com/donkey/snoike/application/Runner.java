package com.donkey.snoike.application;

import com.donkey.snoike.manager.DrawManager;
import com.donkey.snoike.model.GameField;
import com.donkey.snoike.model.Snoike;
import com.donkey.snoike.view.GameFrame;
import com.donkey.snoike.view.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private Snoike snoike;
  private GamePanel gamePanel;
  private GameFrame gameFrame;
  private GameField gameField;

  boolean isGameOver = false;
  DrawManager drawManager = new DrawManager();

  public Runner() {
    this.snoike = new Snoike(new Rectangle(187,180, 10, 10));
    this.gameField = new GameField(new Rectangle(375, 375));
    this.gamePanel = new GamePanel(this::draw, gameField);
    this.gameFrame = new GameFrame(gamePanel);
  }
  private void draw(Graphics2D g) {
    if(gameField.contains(snoike.getPosition())) {
      drawManager.drawSnake(g, snoike);
      log.info("drew snake @ (x: {}, y: {}, width: {}, height: {})",
          snoike.getPosition().getX(),
          snoike.getPosition().getY(),
          snoike.getPosition().getWidth(),
          snoike.getPosition().getHeight());
    } else {
      isGameOver = true;
      drawManager.drawGameOver(g);
    }
  }

  @Scheduled(fixedDelay = 100)
  public void moveSnake() {
    if (!isGameOver) {
      snoike.getPosition().translate(5, -5);
      log.info("snake was moved to (x: {}, y: {})", snoike.getPosition().getX(), snoike.getPosition().getY());
      gamePanel.repaint();
    }
  }
  @Override
  public void run(String... strings) throws Exception {
    gameFrame.setVisible(true);
  }
}
