package edu.school42;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cell {

    private int horisonalPosition;
    private int verticalPosition;
    private static ArrayList<Cell> exploredCells;
    Pathfinder pathfinder;
    static {
        exploredCells = new ArrayList<Cell>();
    }
    private Cell (int horisonalPosition, int verticalPosition, Pathfinder pathfinder) {
        this.horisonalPosition = horisonalPosition;
        this.verticalPosition = verticalPosition;
        this.pathfinder = pathfinder;
    }
    public Cell (Pathfinder pathfinder) {
        horisonalPosition = 0;
        verticalPosition = 0;
    }
    public Cell exploreUp() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && pathfinder.moveUp()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.pathfinder);
        }
        return null;
    }
    public Cell exploreDown() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && pathfinder.moveDown()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.pathfinder);
        }
        return null;
    }
    public Cell exploreRight() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && pathfinder.moveRight()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.pathfinder);
        }
        return null;
    }
    public Cell exploreLeft() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && pathfinder.moveLeft()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.pathfinder);
        }
        return null;
    }
    public static boolean isCellExist(int xPosition, int yPosition) {
        for (int counter = 0; counter < exploredCells.size(); ++counter) {
            if (exploredCells.get(counter).horisonalPosition == xPosition
                    && exploredCells.get(counter).verticalPosition == yPosition) {
                return true;
            }
        }
        return false;
    }
    public LinkedList<Cell> findLinePathToOtherCell(Cell cell) {
        LinkedList<Cell> resultPath = new LinkedList<Cell>();
        if (this.horisonalPosition == cell.horisonalPosition) {
            int verticalDifference = cell.verticalPosition - this.verticalPosition;
            if (verticalDifference > 1) {
                while (pathfinder.moveUp() && verticalDifference > 1) {
                    resultPath.add(new Cell(cell.horisonalPosition,
                            cell.verticalPosition - (verticalDifference - 1),
                            this.pathfinder));
                    --verticalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    pathfinder.moveDown();
                }
                if (verticalDifference == 1) {
                    return resultPath;
                }
            } else if (verticalDifference < -1) {
                while (pathfinder.moveDown() && verticalDifference < -1) {
                    resultPath.add(new Cell(cell.horisonalPosition,
                            cell.verticalPosition - (verticalDifference + 1),
                            this.pathfinder));
                    ++verticalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    pathfinder.moveUp();
                }
                if (verticalDifference == -1) {
                    return resultPath;
                }
            }
        } else if (this.verticalPosition == cell.verticalPosition) {
            int horisontalDifference = cell.horisonalPosition - this.horisonalPosition;
            if (horisontalDifference > 1) {
                while (pathfinder.moveRight() && horisontalDifference > 1) {
                    resultPath.add(new Cell(cell.verticalPosition,
                            cell.horisonalPosition - (horisontalDifference - 1),
                            this.pathfinder));
                    --horisontalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    pathfinder.moveLeft();
                }
                if (horisontalDifference == 1) {
                    return resultPath;
                }
            } else if (horisontalDifference < -1) {
                while (pathfinder.moveLeft() && horisontalDifference < -1) {
                    resultPath.add(new Cell(cell.horisonalPosition,
                            cell.verticalPosition - (horisontalDifference + 1),
                            this.pathfinder));
                    ++horisontalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    pathfinder.moveRight();
                }
                if (horisontalDifference == -1) {
                    return resultPath;
                }
            }
        }
        return null;
    }

    boolean isOnSameHorizontalLine(Cell cell) {
        return this.horisonalPosition == cell.horisonalPosition;
    }
    boolean isOnSameVerticalLine(Cell cell) {
        return this.verticalPosition == cell.verticalPosition;
    }

}

