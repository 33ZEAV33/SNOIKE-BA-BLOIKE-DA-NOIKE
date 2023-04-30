package com.donkey.snoike.manager;

import com.donkey.snoike.model.BodySegment;
import com.donkey.snoike.model.Direction;
import com.donkey.snoike.model.Snoike;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnoikeManager {
    private Snoike snoike;
    private SnakeKeyAdapter snakeKeyAdapter;
    private ConcurrentHashMap<Rectangle, Direction> directionalChanges;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public SnoikeManager() {
        this.snakeKeyAdapter = new SnakeKeyAdapter();
        this.snoike = new Snoike(new Rectangle(185,185, 10, 10));
        this.directionalChanges = new ConcurrentHashMap<>();

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
        if (tail.getDirection()==Direction.UP) {
            newPosition.translate(0, 10);
        } else if (tail.getDirection()==Direction.LEFT) {
            newPosition.translate(10, 0);
        } else if (tail.getDirection()==Direction.DOWN) {
            newPosition.translate(0, -10);
        } else if (tail.getDirection()==Direction.RIGHT) {
            newPosition.translate(-10, 0);
        }
        BodySegment newBodySegment = new BodySegment(newPosition);
        newBodySegment.setDirection(tail.getDirection());
      bodySegmentList.add(newBodySegment);
    }
    public void moveItSnoike(){
        List<BodySegment> bodySegmentList = getBodySegments();
        for (BodySegment bodySegment : bodySegmentList) {
            if (bodySegment.getDirection() == Direction.UP) {
                bodySegment.getPosition().translate(0, -1);
            } else if (bodySegment.getDirection() == Direction.DOWN) {
                bodySegment.getPosition().translate(0, 1);
            } else if (bodySegment.getDirection() == Direction.LEFT) {
                bodySegment.getPosition().translate(-1, 0);
            } else if (bodySegment.getDirection() == Direction.RIGHT) {
                bodySegment.getPosition().translate(1, 0);
            }
        }
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
            Direction direction = snoike.getSnoikeHead().getDirection();
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (direction != Direction.RIGHT)) {
                direction = Direction.LEFT;
                directionalChanges.put(snoike.getPosition(), Direction.LEFT);
                log.info("SAVED DIRECTIONAL CHANGE: POS({}), DIRECTION({})", snoike.getPosition(), Direction.LEFT);
            }
            else if ((key == KeyEvent.VK_RIGHT) && (direction != Direction.LEFT)) {
                direction = Direction.RIGHT;
                directionalChanges.put(snoike.getPosition(), Direction.RIGHT);
                log.info("SAVED DIRECTIONAL CHANGE: POS({}), DIRECTION({})", snoike.getPosition(), Direction.RIGHT);
            }
            else if ((key == KeyEvent.VK_UP) && (direction != Direction.DOWN)) {
                direction = Direction.UP;
                directionalChanges.put(snoike.getPosition(), Direction.UP);
                log.info("SAVED DIRECTIONAL CHANGE: POS({}), DIRECTION({})", snoike.getPosition(), Direction.UP);
            }
            else if ((key == KeyEvent.VK_DOWN) && (direction != Direction.UP)) {
                direction = Direction.DOWN;
                directionalChanges.put(snoike.getPosition(), Direction.DOWN);
                log.info("SAVED DIRECTIONAL CHANGE: POS({}), DIRECTION({})", snoike.getPosition(), Direction.DOWN);
            }
            snoike.getSnoikeHead().setDirection(direction);
        }
    }
}
