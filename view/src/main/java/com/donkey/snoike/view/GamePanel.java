package com.donkey.snoike.view;
import java.util.function.Consumer;
import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
  private Consumer<Graphics2D> graphics;

  public GamePanel(Consumer<Graphics2D> graphics)  {
    setBackground(Color.BLACK);
    setFocusable(true);
    this.graphics = graphics;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics2D = (Graphics2D)g;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
  }
}
