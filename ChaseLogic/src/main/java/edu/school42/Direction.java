package edu.school42;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    STAY;
    public Direction getOppositeDirection() {

        // this will refer to the object SMALL
        switch(this) {
            case UP:
                return DOWN;

            case DOWN:
                return UP;

            case LEFT:
                return RIGHT;

            case RIGHT:
                return LEFT;

            default:
                return STAY;
        }
    }
}
