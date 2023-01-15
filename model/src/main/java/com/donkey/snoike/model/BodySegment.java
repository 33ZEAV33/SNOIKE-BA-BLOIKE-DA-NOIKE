package com.donkey.snoike.model;

import java.awt.Rectangle;

public class BodySegment {
  private Rectangle position;

  public BodySegment() {
    this.position = new Rectangle(187,180, 50, 50);
  }

  public Rectangle getPosition() {
    return position;
  }
}
