package edu.school42;

import java.util.LinkedList;

public class Path {
    private LinkedList<Direction> pathway;

    private Path() {}
    public static Path newInstance(Pathfinder pathfinder) {
        Path path = new Path();
        return path;
    }

    public void makeShort() {

    }
    public Direction getMoveDirection() {
        return Direction.UP;
    }
}
