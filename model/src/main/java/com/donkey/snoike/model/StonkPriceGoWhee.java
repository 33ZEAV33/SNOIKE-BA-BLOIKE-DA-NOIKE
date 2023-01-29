package com.donkey.snoike.model;

import java.awt.Rectangle;

public class StonkPriceGoWhee {
  double stonkValue;
  Rectangle position;

  public StonkPriceGoWhee(double stonkValue, Rectangle position) {
    this.stonkValue = stonkValue;
    this.position = position;
  }

  public StonkPriceGoWhee(Rectangle position) {
    this.position = position;
    this.stonkValue = 0;
  }
}
