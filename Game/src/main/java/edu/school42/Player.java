package edu.school42;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

public class Player extends MovableObject {
    public Player(String playerSymbol, Attribute color, Map map, GameObject target) {
        super(playerSymbol, color, map, target);
    }
    @Override
    public void Update() throws GameOverException {
        Path path = Path.newInstance(this.getPathfinder());
        if (path.getMoveDirection() == Direction.STAY) {
            throw new GameOverException("You lose");
        }
        Scanner console = new Scanner(System.in);
        while (true) {
            String nextLine = console.nextLine();
            if (nextLine.length() > 0) {
                switch (nextLine.charAt(0)) {
                    case 'w':
                        return;
                    case 's':
                        return;
                    case 'a':
                        return;
                    case 'd':
                        return;
                }
            }
        }
    }
}
