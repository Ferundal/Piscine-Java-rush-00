import java.util.ArrayList;
import java.util.LinkedList;

public class Cell {

    private int horisonalPosition;
    private int verticalPosition;
    private static ArrayList<Cell> exploredCells;
    Map map;
    static {
        exploredCells = new ArrayList<Cell>();
    }
    private Cell (int horisonalPosition, int verticalPosition, Map map) {
        this.horisonalPosition = horisonalPosition;
        this.verticalPosition = verticalPosition;
        this.map = map;
    }
    public Cell (Map map) {
        horisonalPosition = 0;
        verticalPosition = 0;
    }
    public Cell exploreUp() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && map.moveUp()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.map);
        }
        return null;
    }
    public Cell exploreDown() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && map.moveDown()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.map);
        }
        return null;
    }
    public Cell exploreRight() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && map.moveRight()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.map);
        }
        return null;
    }
    public Cell exploreLeft() {
        if (!isCellExist(this.horisonalPosition, this.verticalPosition + 1)
                && map.moveLeft()) {
            return new Cell(this.horisonalPosition, this.verticalPosition + 1, this.map);
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
                while (map.moveUp() && verticalDifference > 1) {
                    resultPath.add(new Cell(cell.horisonalPosition,
                            cell.verticalPosition - (verticalDifference - 1),
                            this.map));
                    --verticalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    map.moveDown();
                }
                if (verticalDifference == 1) {
                    return resultPath;
                }
            } else if (verticalDifference < -1) {
                while (map.moveDown() && verticalDifference < -1) {
                    resultPath.add(new Cell(cell.horisonalPosition,
                            cell.verticalPosition - (verticalDifference + 1),
                            this.map));
                    ++verticalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    map.moveUp();
                }
                if (verticalDifference == -1) {
                    return resultPath;
                }
            }
        } else if (this.verticalPosition == cell.verticalPosition) {
            int horisontalDifference = cell.horisonalPosition - this.horisonalPosition;
            if (horisontalDifference > 1) {
                while (map.moveRight() && horisontalDifference > 1) {
                    resultPath.add(new Cell(cell.verticalPosition,
                            cell.horisonalPosition - (horisontalDifference - 1),
                            this.map));
                    --horisontalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    map.moveLeft();
                }
                if (horisontalDifference == 1) {
                    return resultPath;
                }
            } else if (horisontalDifference < -1) {
                while (map.moveLeft() && horisontalDifference < -1) {
                    resultPath.add(new Cell(cell.horisonalPosition,
                            cell.verticalPosition - (horisontalDifference + 1),
                            this.map));
                    ++horisontalDifference;
                }
                for (int counter = 0; counter < resultPath.size(); ++counter) {
                    map.moveRight();
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

