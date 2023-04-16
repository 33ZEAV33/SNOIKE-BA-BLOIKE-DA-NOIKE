package com.donkey.snoike.application;

import com.donkey.snoike.manager.DrawManager;
import com.donkey.snoike.manager.SnoikeManager;
import com.donkey.snoike.manager.StonkPriceGoWheeManager;
import com.donkey.snoike.model.BodySegment;
import com.donkey.snoike.model.Direction;
import com.donkey.snoike.model.GameField;
import com.donkey.snoike.model.Snoike;
import com.donkey.snoike.model.StonkPriceGoWhee;
import com.donkey.snoike.view.GameFrame;
import com.donkey.snoike.view.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private GamePanel gamePanel;
  private GameFrame gameFrame;
  private GameField gameField;

  boolean isGameOver = false;
  DrawManager drawManager;
  StonkPriceGoWheeManager stonkPriceGoWheeManager;
  SnoikeManager snoikeManager;

  public Runner() {
    drawManager = new DrawManager();
    stonkPriceGoWheeManager = new StonkPriceGoWheeManager();
    snoikeManager = new SnoikeManager();

    this.gameField = new GameField(new Rectangle(390, 390));
    this.gamePanel = new GamePanel(this::draw, gameField);
    gamePanel.addKeyListener(snoikeManager.getSnakeKeyAdapter());
    this.gameFrame = new GameFrame(gamePanel);

    stonkPriceGoWheeManager.makeWhee(gameField.getBorders(), snoikeManager.getSnoike());
    stonkPriceGoWheeManager.makeWhee(gameField.getBorders(), snoikeManager.getSnoike());
    stonkPriceGoWheeManager.makeWhee(gameField.getBorders(), snoikeManager.getSnoike());
    stonkPriceGoWheeManager.makeWhee(gameField.getBorders(), snoikeManager.getSnoike());
  }
  private void draw(Graphics2D g) {
    if(gameField.contains(snoikeManager.getSnoike().getPosition())) {
      drawManager.drawSnake(g, snoikeManager.getSnoike());
      drawManager.drawWhees(g, stonkPriceGoWheeManager.getReginaldsStonkPriceGoWheeCollection().values());
//      log.info("drew snake @ (x: {}, y: {}, width: {}, height: {})",
//              snoikeManager.getSnoike().getPosition().getX(),
//              snoikeManager.getSnoike().getPosition().getY(),
//              snoikeManager.getSnoike().getPosition().getWidth(),
//              snoikeManager.getSnoike().getPosition().getHeight());
    } else {
      isGameOver = true;
      drawManager.drawGameOver(g);
    }
  }

  @Scheduled(fixedDelay = 40)
  public void moveSnake() {
    if (isGameOver) {
      return;
    }

    List<BodySegment> bodySegmentList = snoikeManager.getBodySegments();
    for (BodySegment bodySegment : bodySegmentList) {
      if (snoikeManager.getSnoike().getDirection() == Direction.UP) {
        bodySegment.getPosition().translate(0, -1);
      } else if (snoikeManager.getSnoike().getDirection() == Direction.DOWN) {
        bodySegment.getPosition().translate(0, 1);
      } else if (snoikeManager.getSnoike().getDirection() == Direction.LEFT) {
        bodySegment.getPosition().translate(-1, 0);
      } else if (snoikeManager.getSnoike().getDirection() == Direction.RIGHT) {
        bodySegment.getPosition().translate(1, 0);
      }

    }
    //log.info("snake was moved to (x: {}, y: {})", snoikeManager.getSnoike().getPosition().getX(), snoikeManager.getSnoike().getPosition().getY());
    for (StonkPriceGoWhee whee : stonkPriceGoWheeManager.getReginaldsStonkPriceGoWheeCollection().values()) {
      if (intersectsByAtLeastSeventyFivePercent(snoikeManager.getSnoike().getSnoikeHead().getPosition(), whee.getPosition())) {
        stonkPriceGoWheeManager.removeWhee(whee);
        stonkPriceGoWheeManager.makeWhee(gameField.getBorders(), snoikeManager.getSnoike());
        snoikeManager.growSnake();
      }
    }
    gamePanel.repaint();
  }

    public static boolean intersectsByAtLeastSeventyFivePercent(Rectangle r1, Rectangle r2) {
      Rectangle intersection = r1.intersection(r2);

      if (intersection.isEmpty()) {
        return false;
      }

      double intersectionArea = intersection.getWidth() * intersection.getHeight();
      double smallerArea = Math.min(r1.getWidth() * r1.getHeight(), r2.getWidth() * r2.getHeight());

      double percentageOverlap = (intersectionArea / smallerArea) * 100;

      return percentageOverlap >= 50.0;
    }

  @Override
  public void run(String... strings) throws Exception {
    gameFrame.setVisible(true);
  }
}
