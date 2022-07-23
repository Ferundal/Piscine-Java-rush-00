package edu.school42;

import com.diogonunes.jcolor.Attribute;
import java.util.Scanner;

public class Enemy extends MovableObject {
    boolean isDevMode;
    public Enemy(String playerSymbol, Attribute color, Map map, GameObject target, boolean isDevMode) {
        super(playerSymbol, color, map, target);
        this.isDevMode = isDevMode;
    }

    @Override
    public boolean Update() throws GameOverException {
        if (isDevMode) {
            Scanner console = new Scanner(System.in);
            while (true) {
                String nextLine = console.nextLine();
                if (nextLine.length() > 0 && nextLine.charAt(0) == '8') {
                    break;
                }
            }
        }
        return false;
    }
}
