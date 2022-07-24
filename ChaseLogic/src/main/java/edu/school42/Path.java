package edu.school42;

import java.util.ArrayList;
import java.util.LinkedList;

public class Path {
    private LinkedList<Direction> pathway;

    private Path() {}
    public static Path newInstance(Pathfinder pathfinder) {
        Path path = new Path();
        Cell cell = new Cell(pathfinder);
        while (cell.expandWave()) {
            path.pathway = cell.getPath();
            if (path.pathway != null) {
                return path;
            }
        }
        return path;
    }

    public Direction getMoveDirection() {
        if (this.pathway == null) {
            return Direction.STAY;
        }
        return this.pathway.getFirst();
    }
}
