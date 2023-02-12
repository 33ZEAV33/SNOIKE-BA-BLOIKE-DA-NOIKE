package com.donkey.snoike.manager;

import com.donkey.snoike.model.StonkPriceGoWhee;
import com.donkey.snoike.model.Snoike;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawManager {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public void drawWhees(Graphics2D graphics2D, List<StonkPriceGoWhee> whees) {
    for(StonkPriceGoWhee whee: whees) {
      drawStonkPriceGoWhee(graphics2D, whee);
    }
  }
  public void drawStonkPriceGoWhee(Graphics2D graphics2D, StonkPriceGoWhee stonkPriceGoWhee) {
    graphics2D.setColor(Color.decode("#81C894"));
    Rectangle2D stonkPriceRectangle = new Rectangle2D.Double(
        stonkPriceGoWhee.getPosition().getX(),
        stonkPriceGoWhee.getPosition().getY(),
        stonkPriceGoWhee.getPosition().getWidth(),
        stonkPriceGoWhee.getPosition().getHeight()
    );
    graphics2D.draw(stonkPriceRectangle);
    graphics2D.fill(stonkPriceRectangle);
  }

  public void drawSnake(Graphics2D g, Snoike snoike) {
    g.setColor(Color.decode("#d9bd62"));
    Rectangle2D head2D = new Rectangle2D.Double(
        snoike.getPosition().getX(),
        snoike.getPosition().getY(),
        snoike.getPosition().getWidth(),
        snoike.getPosition().getHeight());

    g.draw(head2D);
    g.fill(head2D);
  }

  public void drawGameOver(Graphics2D g) {
    g.setColor(Color.decode("#f28b82"));
    g.drawString("GAME OVER IDIOT YOU LOST", 98, 179);
  }
}
