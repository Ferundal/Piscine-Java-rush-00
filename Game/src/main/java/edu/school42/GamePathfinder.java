package edu.school42;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePathfinder implements Pathfinder {
    private Map map;
    public int xPosition;
    public int yPosition;
    private List<GameObject> obstacles;
    private GameObject target;

    {
        this.obstacles = new ArrayList<GameObject>();
    }

    private GamePathfinder() {}
    public GamePathfinder(Map map, MovableObject movableObject, GameObject target) {
        this.map = map;
        this.xPosition = movableObject.xPosition;
        this.yPosition = movableObject.yPosition;
        this.target = target;
    }
    public void addObstacle(GameObject obstacle) {
        if (!obstacles.contains(obstacle)) {
            obstacles.add(obstacle);
        }
    }

    @Override
    public boolean move(Direction direction) {
        switch (direction) {
            case UP:
                return moveUp();
            case DOWN:
                return moveDown();
            case RIGHT:
                return moveRight();
            case LEFT:
                return moveLeft();
        }
        return true;
    }

    @Override
    public boolean moveUp() {
        if (this.yPosition == 0) {
            return false;
        }
        GameObject cellValue = this.map.getCellValue(this.xPosition, this.yPosition - 1);
        if (cellValue != null && obstacles.contains(cellValue)) {
            return false;
        }
        --this.yPosition;
        return true;
    }

    @Override
    public boolean moveDown() {
        if (this.yPosition == map.getY_Size() - 1) {
            return false;
        }
        GameObject cellValue = this.map.getCellValue(this.xPosition, this.yPosition + 1);
        if (cellValue != null && obstacles.contains(cellValue)) {
            return false;
        }
        ++this.yPosition;
        return true;
    }

    @Override
    public boolean moveLeft() {
        if (this.xPosition == 0) {
            return false;
        }
        GameObject cellValue = this.map.getCellValue(this.xPosition - 1, this.yPosition);
        if (cellValue != null && obstacles.contains(cellValue)) {
            return false;
        }
        --this.xPosition;
        return true;
    }

    @Override
    public boolean moveRight() {
        if (this.xPosition == map.getX_Size() - 1) {
            return false;
        }
        GameObject cellValue = this.map.getCellValue(this.xPosition + 1, this.yPosition);
        if (cellValue != null && obstacles.contains(cellValue)) {
            return false;
        }
        ++this.xPosition;
        return true;
    }

    @Override
    public boolean isTarget() {
        GameObject cellValue = this.map.getCellValue(this.xPosition, this.yPosition);
        if (cellValue == target) {
            return true;
        }
        return false;
    }
}
