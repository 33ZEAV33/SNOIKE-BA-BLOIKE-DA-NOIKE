package com.donkey.snoike.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {
  public GameFrame(GamePanel gamePanel) {
    add(gamePanel);
    setSize(375, 375);
    setResizable(false);
    //pack();
    setTitle("SNOIKE-BA-KLOIKE-DA-NOIKE");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
