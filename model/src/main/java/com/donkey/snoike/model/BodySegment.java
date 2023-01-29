package com.donkey.snoike.model;

import java.awt.Rectangle;

public class BodySegment {
  private Rectangle position;

  public BodySegment(Rectangle position) {
    this.position = position;
  }

  public Rectangle getPosition() {
    return position;
  }
}
