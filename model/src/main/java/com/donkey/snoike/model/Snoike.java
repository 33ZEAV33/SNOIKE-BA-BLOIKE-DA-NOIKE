package com.donkey.snoike.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snoike {
    private Rectangle position;
    private Direction direction;
    private List<BodySegment> bodySegments;
    public BodySegment getSnoikeHead(){
        return bodySegments.get(0);
    }

    public Snoike(Rectangle rectangle) {
        this.bodySegments = new ArrayList<>();
        bodySegments.add(new BodySegment(rectangle));
        this.position = getSnoikeHead().getPosition();
        this.direction = Direction.UP;

    }
    public List<BodySegment> getBodySegments() {
        return bodySegments;
    }

    public List<BodySegment> getNonHeadBodySegments() {
        List<BodySegment> nonheadbodysegments = new ArrayList<>();
        for(int index = 1; index < bodySegments.size(); index++){
            nonheadbodysegments.add(bodySegments.get(index));
        }
        return nonheadbodysegments;
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
