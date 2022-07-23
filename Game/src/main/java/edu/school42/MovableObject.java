package edu.school42;

import com.diogonunes.jcolor.Attribute;

public abstract class MovableObject extends GameObject {
    protected GamePathfinder pathfinder;

    public int xPosition;
    public int yPosition;
    public MovableObject (String playerSymbol, Attribute color, Map map, GameObject target) {
        super(playerSymbol, color);
        pathfinder = new GamePathfinder(map, this, target);
    }

    public void addObstacle(GameObject obstacle) {
        pathfinder.addObstacle(obstacle);
    }
    public abstract boolean Update() throws GameOverException;

    public GamePathfinder getPathfinder() {
        pathfinder.xPosition = this.xPosition;
        pathfinder.yPosition = this.yPosition;
        return pathfinder;
    }
}
