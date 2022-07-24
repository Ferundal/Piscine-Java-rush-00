package edu.school42;

public interface Pathfinder {

    boolean move(Direction direction);
    boolean moveUp();
    boolean moveDown();
    boolean moveLeft();
    boolean moveRight();
    boolean isTarget();
}
