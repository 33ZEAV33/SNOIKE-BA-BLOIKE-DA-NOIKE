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

    public List<BodySegment> getBodySegments() {
        List<BodySegment> nonheadbodysegments = new ArrayList<>();
        for(int index = 1; index < bodySegments.size(); index++){
            nonheadbodysegments.add(bodySegments.get(index));
        }
        return nonheadbodysegments;
    }
}
