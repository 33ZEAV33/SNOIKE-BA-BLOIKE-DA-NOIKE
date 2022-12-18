package com.donkey.snoike.collision;

import java.awt.geom.Area;

public class GameCollisionController
{
    private Area collisionArea;
    private SnoikeCollisionController snoikeCollisionController;
    private FruitCollisionController fruitCollisionController;
    public void refreshCollisionArea(){
        snoikeCollisionController.refreshCollisionArea();
        fruitCollisionController.refreshCollisionArea();
        collisionArea = new Area();
        collisionArea.add(snoikeCollisionController.getCollisionArea());
        collisionArea.add(fruitCollisionController.getCollisionArea());
    }
}
