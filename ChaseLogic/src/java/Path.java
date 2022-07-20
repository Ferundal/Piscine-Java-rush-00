import java.util.LinkedList;

public class Path {
    Map map;
    boolean isSuccess;
    LinkedList<Cell> pathCells;
    private Cell currentPosition;
    private Path () {}

    public static Path findPath(Map map) {
        Path newPath = new Path (map, new LinkedList<Cell>(), new Cell(map));
        newPath.explore();
        if (newPath.isSuccess) {
            return newPath;
        }
        return null;
    }

    private Path (Map map, LinkedList<Cell> pathCells, Cell cellToExplore) {
        isSuccess = false;
        this.map = map;
        this.pathCells = pathCells;
        this.pathCells.add(cellToExplore);
        this.currentPosition = cellToExplore;
    }

    private void explore() {
        if (this.map.isTarget()) {
            this.isSuccess = true;
        }
        Path newPath;
        Cell newCell;
        if ((newCell = this.currentPosition.exploreUp()) != null) {
            newPath = new Path(this.map, new LinkedList<Cell>(), newCell);
            newPath.explore();
            if (newPath.isSuccess) {
                this.pathCells.addAll(newPath.pathCells);
                this.isSuccess = true;
                map.moveDown();
            }
        } else if ((newCell = this.currentPosition.exploreDown()) != null) {
            newPath = new Path(this.map, new LinkedList<Cell>(), newCell);
            newPath.explore();
            if (newPath.isSuccess) {
                this.pathCells.addAll(newPath.pathCells);
                this.isSuccess = true;
                map.moveUp();
            }
        } else if ((newCell = this.currentPosition.exploreRight()) != null) {
            newPath = new Path(this.map, new LinkedList<Cell>(), newCell);
            newPath.explore();
            if (newPath.isSuccess) {
                this.pathCells.addAll(newPath.pathCells);
                this.isSuccess = true;
                map.moveLeft();
            }
        } else if ((newCell = this.currentPosition.exploreLeft()) != null) {
            newPath = new Path(this.map, new LinkedList<Cell>(), newCell);
            newPath.explore();
            if (newPath.isSuccess) {
                this.pathCells.addAll(newPath.pathCells);
                this.isSuccess = true;
                map.moveRight();
            }
        }
    }
    private void makeShort() {
        int listEndOffset = 0;
        while (pathCells.size() - listEndOffset > 0) {
            if (this.currentPosition.findLinePathToOtherCell(pathCells.get(pathCells.size() - listEndOffset - 1)) != null) {
                LinkedList<Cell> newList = new LinkedList<Cell>();
            }
        }
    }
}
