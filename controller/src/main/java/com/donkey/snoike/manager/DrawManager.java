package com.donkey.snoike.manager;

import com.donkey.snoike.model.Fruit;
import com.donkey.snoike.model.Snoike;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawManager {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public void drawFruits(Graphics2D g, List<Fruit> fruits) {
    //not implemented yet
  }

  public void drawSnake(Graphics2D g, Snoike snoike) {
    g.setColor(Color.decode("#d9bd62"));
    log.info("SNOIKE COLOR SET TO: {}", g.getColor());
    Rectangle2D head2D = new Rectangle2D.Double(
        snoike.getPosition().getX(),
        snoike.getPosition().getY(),
        10, 10);
    log.info("PAINTING SNOIKE HEAD: {}", head2D);

    g.draw(head2D);
    g.fill(head2D);
  }

  public void drawGameOver(Graphics2D g) {
    g.setColor(Color.decode("#f28b82"));
    g.drawString("GAME OVER IDIOT YOU LOST", 98, 179);
  }

  public void drawBorders(Graphics2D g, Rectangle borders) {
    g.setColor(Color.decode("#f28b82"));
    Rectangle fitBorders = new Rectangle(borders);
    fitBorders.grow(-1, -19);
    fitBorders.setLocation(0, 1);
    g.draw(fitBorders);
  }
}
