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
  DrawManager drawManager = new DrawManager();

  public Runner() {
    this.snoike = new Snoike();
    this.gameField = new GameField(new Rectangle(375, 375));
    this.gamePanel = new GamePanel(this::draw);
    this.gameFrame = new GameFrame(gamePanel, gameField.getBorders());
  }
  private void draw(Graphics2D g) {
    drawManager.drawBorders(g, gameField.getBorders());
    if(gameField.contains(snoike.getPosition())) {
      drawManager.drawSnake(g, snoike);
    } else {
      drawManager.drawGameOver(g);
    }
  }

  @Scheduled(fixedDelay = 200)
  public void moveSnake() {
    //snoike.getPosition().translate(5, -5);
    log.info("snake position at: {}", snoike.getPosition());

    gamePanel.repaint();
  }
  @Override
  public void run(String... strings) throws Exception {
    log.info("setting frame visibility");
    gameFrame.setVisible(true);
  }
}
