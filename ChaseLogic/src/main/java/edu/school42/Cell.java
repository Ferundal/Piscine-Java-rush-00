package edu.school42;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cell {

    private int horisonalPosition;
    private int verticalPosition;
    private ArrayList<Cell> exploredCells;
    private ArrayList<Cell> connectedCells = null;
    private LinkedList<Direction> path = null;
    private Pathfinder pathfinder;

    private Direction expandDirection;

    private int generation;

    private Cell (Cell parentCell,
                  int horisonalPosition,
                  int verticalPosition,
                  Direction expandDirection) {
        this.generation = ++parentCell.generation;
        this.horisonalPosition = horisonalPosition;
        this.verticalPosition = verticalPosition;
        this.pathfinder = parentCell.pathfinder;
        this.exploredCells = parentCell.exploredCells;
        this.exploredCells.add(this);
        this.expandDirection = expandDirection;
    }
    public Cell (Pathfinder pathfinder) {
        this.generation = 0;
        horisonalPosition = 0;
        verticalPosition = 0;
        this.pathfinder = pathfinder;
        this.exploredCells = new ArrayList<Cell>();
        this.exploredCells.add(this);
        this.expandDirection = Direction.STAY;
    }



    public boolean expandWave() {
        boolean isExpandSucceed = false;
        if (connectedCells == null) {
            connectedCells = new ArrayList<Cell>(4);
            if (exploreDirection(Direction.UP)) {
                isExpandSucceed = true;
            }
            if (exploreDirection(Direction.DOWN)) {
                isExpandSucceed = true;
            }
            if (exploreDirection(Direction.LEFT)) {
                isExpandSucceed = true;
            }
            if (exploreDirection(Direction.RIGHT)) {
                isExpandSucceed = true;
            }
        } else {
            for (Cell connectedCell: connectedCells) {
                pathfinder.move(connectedCell.expandDirection);
                if (connectedCell.expandWave()) {
                    isExpandSucceed = true;
                }
                pathfinder.move(connectedCell.expandDirection.getOppositeDirection());
            }
        }
        for (Cell connectedCell: connectedCells) {
            if (connectedCell.path != null) {
                this.path = connectedCell.path;
                this.path.addFirst(connectedCell.expandDirection);
            }
        }
        return isExpandSucceed;
    }

    public boolean exploreDirection(Direction direction) {
        int cellX_Position = this.horisonalPosition;
        int cellY_Position = this.verticalPosition;
        switch (direction) {
            case UP:
                --cellY_Position;
                break;
            case DOWN:
                ++cellY_Position;
                break;
            case LEFT:
                --cellX_Position;
                break;
            case RIGHT:
                ++cellX_Position;
                break;
        }
        if (!isCellExist(cellX_Position, cellY_Position) && pathfinder.move(direction)) {
            this.connectedCells.add(new Cell(this,
                    cellX_Position,
                    cellY_Position,
                    direction));
            if (pathfinder.isTarget()) {
                this.path = new LinkedList<>();
                path.addFirst(direction);
            }
            pathfinder.move(direction.getOppositeDirection());
            return true;
        }
        return false;
    }



    public boolean isCellExist(int xPosition, int yPosition) {
        for (Cell exploredCell : exploredCells) {
            if (exploredCell.horisonalPosition == xPosition
                    && exploredCell.verticalPosition == yPosition) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Direction> getPath() {
        return path;
    }
}

