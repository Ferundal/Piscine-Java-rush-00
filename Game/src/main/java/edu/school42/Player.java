package edu.school42;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

public class Player extends MovableObject {
    public Player(String playerSymbol, Attribute color, Map map, GameObject target) {
        super(playerSymbol, color, map, target);
    }
    @Override
    public boolean Update() throws GameOverException {
        boolean isGameOver = true;
        if (this.getPathfinder().move(Direction.UP) ||
                this.getPathfinder().move(Direction.DOWN) ||
                this.getPathfinder().move(Direction.RIGHT) ||
                this.getPathfinder().move(Direction.LEFT)) {
            isGameOver = false;
        }
        if (isGameOver) {
            throw new GameOverException("You lose");
        }
        Scanner console = new Scanner(System.in);
        while (true) {
            String nextLine = console.nextLine();
            if (nextLine.length() > 0 && Move(nextLine.charAt(0))) {
                    return true;
                }
            }
        }

    private boolean Move(char moveDirection) throws GameOverException {
        switch (moveDirection) {
            case 'w':
                if (this.getPathfinder().moveUp()) {
                    --this.yPosition;
                    return true;
                }
                break;
            case 's':
                if (this.getPathfinder().moveDown()) {
                    ++this.yPosition;
                    return true;
                }
                break;
            case 'a':
                if (this.getPathfinder().moveLeft()) {
                    --this.xPosition;
                    return true;
                }
                break;
            case 'd':
                if (this.getPathfinder().moveRight()) {
                    ++this.xPosition;
                    return true;
                }
                break;
            case '9':
                throw new GameOverException("You concede defeat");
        }
        return false;
    }
}
