package com.donkey.snoike.manager;

import com.donkey.snoike.model.BodySegment;
import com.donkey.snoike.model.Direction;
import com.donkey.snoike.model.Snoike;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class SnoikeManager {
    private Snoike snoike;
    private SnakeKeyAdapter snakeKeyAdapter;

    public SnoikeManager() {
        this.snakeKeyAdapter = new SnakeKeyAdapter();
        this.snoike = new Snoike(new Rectangle(185,185, 10, 10));

    }

    public Snoike getSnoike() {
        return snoike;
    }
    public Snoike createSnake() {
        return null;
    }
    public void changeDirection(Direction direction){

    }
    public void growSnake(){
      List<BodySegment> bodySegmentList = getSnoike().getBodySegments();
      BodySegment tail = getSnoike().getBodySegments().get(bodySegmentList.size() - 1);
      Rectangle newPosition = new Rectangle(tail.getPosition());
        if (snoike.getDirection()==Direction.UP) {
            newPosition.translate(0, 10);
        } else if (snoike.getDirection()==Direction.LEFT) {
            newPosition.translate(10, 0);
        } else if (snoike.getDirection()==Direction.DOWN) {
            newPosition.translate(0, -10);
        } else if (snoike.getDirection()==Direction.RIGHT) {
            newPosition.translate(-10, 0);
        }
        BodySegment newBodySegment = new BodySegment(newPosition);
      bodySegmentList.add(newBodySegment);
    }
    public void moveItSnake(){

    }

    public List<BodySegment> getBodySegments() {
        return getSnoike().getBodySegments();
    }

    public SnakeKeyAdapter getSnakeKeyAdapter() {
        return snakeKeyAdapter;
    }

    public class SnakeKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            Direction direction = snoike.getDirection();
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (direction != Direction.RIGHT)) {
                direction = Direction.LEFT;
            }
            else if ((key == KeyEvent.VK_RIGHT) && (direction != Direction.LEFT)) {
                direction = Direction.RIGHT;
            }
            else if ((key == KeyEvent.VK_UP) && (direction != Direction.DOWN)) {
                direction = Direction.UP;
            }
            else if ((key == KeyEvent.VK_DOWN) && (direction != Direction.UP)) {
                direction = Direction.DOWN;
            }
            snoike.setDirection(direction);
        }
    }
}
