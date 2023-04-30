package com.donkey.snoike.model;

import java.awt.Rectangle;

public class BodySegment {
  private Rectangle position;
  private Direction direction;

  public BodySegment(Rectangle position) {
    this.position = position;
  }

  public Rectangle getPosition() {
    return position;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }
}
