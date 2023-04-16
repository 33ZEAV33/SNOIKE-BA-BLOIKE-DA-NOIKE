package com.donkey.snoike.manager;

import com.donkey.snoike.model.BodySegment;
import com.donkey.snoike.model.Snoike;
import com.donkey.snoike.model.StonkPriceGoWhee;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.swing.text.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StonkPriceGoWheeManager {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ConcurrentHashMap<Rectangle, StonkPriceGoWhee> reginaldsStonkPriceGoWheeCollection;
    private ConcurrentHashMap<Point, Rectangle> freePositions;

    public StonkPriceGoWheeManager() {
        this.reginaldsStonkPriceGoWheeCollection = new ConcurrentHashMap<>();
    }
    private void addWhee(StonkPriceGoWhee stonkPriceGoWhee) {
        reginaldsStonkPriceGoWheeCollection.put(stonkPriceGoWhee.getPosition(), stonkPriceGoWhee);
    }
    public void removeWhee(StonkPriceGoWhee stonkPriceGoWhee){
        reginaldsStonkPriceGoWheeCollection.remove(stonkPriceGoWhee.getPosition());
    }
    public void makeWhee(Rectangle borders, Snoike snoike) {
        ConcurrentHashMap<Point, Rectangle> freePositions = generateFreePositions(borders, snoike);
        int numberOfFreePositions = freePositions.size();
        log.info("num free positions: {}", numberOfFreePositions);
        if(numberOfFreePositions == 0) {
            return;
        }
        Random random = new Random();
        int randomIndex = 0;
        if (numberOfFreePositions > 1) {
            randomIndex = random.nextInt(numberOfFreePositions - 1);
        }
        try {
            StonkPriceGoWhee stonkPriceGoWhee = new StonkPriceGoWhee(new ArrayList<>(freePositions.values()).get(randomIndex));
            log.info("NEW WHEE: " + stonkPriceGoWhee.getPosition());
            addWhee(stonkPriceGoWhee);
            log.info("NEW SNAKE SIZE: " + snoike.getBodySegments().size());
        } catch (Exception e) {
            log.info("FREE POSITIONS: {}", numberOfFreePositions);
            log.info("RANDOM INDEX: {}", randomIndex);
        }
    }

    private ConcurrentHashMap<Point, Rectangle> generateFreePositions(Rectangle borders, Snoike snoike) {
        if(freePositions == null) {
            freePositions = new ConcurrentHashMap<>();
            List<Integer> potentialXPositions = new ArrayList<>();
            for(int i = 0; i < borders.getMaxX(); i += 10) {
                potentialXPositions.add(i);
            }
            List<Integer> potentialYPositions = new ArrayList<>();
            for(int i = 0; i < borders.getMaxY(); i += 10) {
                potentialYPositions.add(i);
            }

            for(Integer potentialX: potentialXPositions) {
                for(Integer potentialY: potentialYPositions) {
                    Rectangle potentialPosition = new Rectangle(potentialX, potentialY, 10, 10);
                    freePositions.put(potentialPosition.getLocation(), potentialPosition);
                }
            }
        }

        for(Rectangle freePosition: freePositions.values()) {
            Rectangle potentialPosition = freePosition;
            for(BodySegment bodySegment: snoike.getBodySegments()) {
                if (bodySegment.getPosition().intersects(potentialPosition)) {
                    freePositions.remove(freePosition.getLocation());
                }
            }
            for(StonkPriceGoWhee whee: reginaldsStonkPriceGoWheeCollection.values()) {
                if (whee.getPosition().intersects(potentialPosition)) {
                    freePositions.remove(freePosition.getLocation());
                }
            }
        }
        return freePositions;
    }

    public Map<Rectangle, StonkPriceGoWhee> getReginaldsStonkPriceGoWheeCollection() {
        return reginaldsStonkPriceGoWheeCollection;
    }

    //public List<Rectangle> getFreePositions() {
//        return freePositions;
//    }

}