package com.donkey.snoike.view;

import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameFrame extends JFrame {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public GameFrame(GamePanel gamePanel, Rectangle borders) {
    add(gamePanel);
    setSize((int) borders.getWidth(), (int)borders.getHeight());
    setResizable(false);
    //pack();
    setTitle("SNOIKE-BA-KLOIKE-DA-NOIKE");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    log.info("CREATED GAMEE FRAME");
  }
}
