package com.donkey.snoike.view;
import com.donkey.snoike.model.GameField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.function.Consumer;
import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private Consumer<Graphics2D> graphics;

  private GameField gameField;

  public GamePanel(Consumer<Graphics2D> graphics, GameField gameField)  {
    this.gameField = gameField;
    this.graphics = graphics;
    setBackground(Color.BLACK);
    setFocusable(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension((int)gameField.getBorders().getWidth(), (int)gameField.getBorders().getHeight());
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics2D = (Graphics2D)g;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    graphics.accept(graphics2D);
  }
}
