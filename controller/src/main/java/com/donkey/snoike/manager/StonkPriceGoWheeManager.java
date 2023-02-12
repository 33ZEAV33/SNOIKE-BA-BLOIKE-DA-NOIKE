package com.donkey.snoike.manager;

import com.donkey.snoike.model.BodySegment;
import com.donkey.snoike.model.Snoike;
import com.donkey.snoike.model.StonkPriceGoWhee;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.text.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StonkPriceGoWheeManager {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private List<StonkPriceGoWhee> reginaldsStonkPriceGoWheeCollection;
    private List<Rectangle> freePositions;

    public StonkPriceGoWheeManager() {
        this.reginaldsStonkPriceGoWheeCollection = new ArrayList<>();
        this.freePositions = new ArrayList<>();
    }
    private void addWhee(StonkPriceGoWhee stonkPriceGoWhee) {
        reginaldsStonkPriceGoWheeCollection.add(stonkPriceGoWhee);
    }
    private void removeWhee(StonkPriceGoWhee stonkPriceGoWhee){
        reginaldsStonkPriceGoWheeCollection.remove(stonkPriceGoWhee);
    }
    public void makeWhee(Rectangle borders, Snoike snoike) {
        List<Rectangle> freePositions = generateFreePositions(borders, snoike);
        int numberOfFreePositions = freePositions.size();
        if(numberOfFreePositions == 0) {
            return;
        }
        Random random = new Random();
        int randomIndex = 0;
        if (numberOfFreePositions > 1) {
            randomIndex = random.nextInt(numberOfFreePositions - 1);
        }
        try {
            StonkPriceGoWhee stonkPriceGoWhee = new StonkPriceGoWhee(freePositions.get(randomIndex));
            addWhee(stonkPriceGoWhee);
        } catch (Exception e) {
            log.info("FREE POSITIONS: {}", numberOfFreePositions);
            log.info("RANDOM INDEX: {}", randomIndex);
        }
    }

    private List<Rectangle> generateFreePositions(Rectangle borders, Snoike snoike) {
        List<Rectangle> freePositions = new ArrayList<>();
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
                freePositions.add(potentialPosition);
            }
        }

        Iterator<Rectangle> freePositionsIterator = freePositions.iterator();
        while (freePositionsIterator.hasNext()) {
            Rectangle potentialPosition = freePositionsIterator.next();
            for(BodySegment bodySegment: snoike.getBodySegments()) {
                if (bodySegment.getPosition().intersects(potentialPosition)) {
                    freePositionsIterator.remove();
                }
            }
            for(StonkPriceGoWhee whee: reginaldsStonkPriceGoWheeCollection) {
                if (whee.getPosition().intersects(potentialPosition)) {
                    freePositionsIterator.remove();
                }
            }
        }
        return freePositions;
    }

    public List<StonkPriceGoWhee> getReginaldsStonkPriceGoWheeCollection() {
        return reginaldsStonkPriceGoWheeCollection;
    }

    public List<Rectangle> getFreePositions() {
        return freePositions;
    }

}