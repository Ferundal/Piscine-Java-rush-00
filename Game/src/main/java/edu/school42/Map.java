package edu.school42;

import com.diogonunes.jcolor.Attribute;
import com.diogonunes.jcolor.Command;

import java.util.ArrayList;
import java.util.Random;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BLACK_TEXT;

public class Map {
    ApplicationProperties applicationProperties;
    int objectsCounter = 0;
    private GameObject [][] infoStore;
    private String emptyChar;
    private Attribute emptyColor;
    private GameObject wall;
    private GameObject goal;
    private Player player;
    private ArrayList<Enemy> enemies;
    {
        Integer objectCounter = 0;
        enemies = new ArrayList<Enemy>();
    }
    private Map() {}
    private Map(int size, ApplicationProperties appProperties) {
        this.applicationProperties = appProperties;
        this.infoStore = new GameObject [size][size];
        emptyChar = appProperties.emptyChar;
        emptyColor = appProperties.emptyColor;
    }

    public static Map GenerateMap(int size, int enemiesCount, int wallsCount, ApplicationProperties appProperties) throws IllegalParametersException {
        if (size < 2 || size * size < enemiesCount + wallsCount + 2) {
            throw new IllegalParametersException();
        }
        Path pathToTarget;
        Map map;
        do {
            map = new Map(size, appProperties);

            map.wall = new GameObject(appProperties.wallChar, appProperties.wallColor);
            for (int counter = 0; counter < wallsCount; counter++) {
                map.putAtRandomPosition(map.wall);
            }

            map.goal = new GameObject(appProperties.goalChar, appProperties.goalColor);
            map.putAtRandomPosition(map.goal);

            map.player = new Player(appProperties.playerChar, appProperties.playerColor,
                    map, map.goal);
            map.player.addObstacle(map.wall);
            map.putMovableObject(map.player);

            for (int counter = 0; counter < enemiesCount; counter++) {
                Enemy newEnemy = new Enemy(appProperties.enemyChar, appProperties.enemyColor, map, map.player, appProperties.isDevMode);
                newEnemy.addObstacle(map.wall);
                newEnemy.addObstacle(map.goal);
                newEnemy.addObstacle(map.player);
                map.player.addObstacle(newEnemy);
                for (Enemy currentEnemy : map.enemies) {
                    newEnemy.addObstacle(currentEnemy);
                    currentEnemy.addObstacle(newEnemy);
                }
                map.enemies.add(newEnemy);
                map.putMovableObject(newEnemy);
            }
            pathToTarget = Path.newInstance(map.player.getPathfinder());
        } while (pathToTarget.getMoveDirection() == Direction.STAY);
        return (map);
    }

    public GameObject getCellValue(int xPosition, int yPosition) {
        return infoStore[yPosition][xPosition];
    }


    private void putMovableObject(MovableObject movableObject) {
        int position = new Random().nextInt(this.infoStore.length * this.infoStore[0].length - this.objectsCounter);
        for (int outerCounter = 0; outerCounter < infoStore.length; ++outerCounter) {
            for (int innerCounter = 0; innerCounter < this.infoStore[0].length; ++innerCounter) {
                if (this.infoStore[outerCounter][innerCounter] == null) {
                    if (position == 0) {
                        this.infoStore[outerCounter][innerCounter] = movableObject;
                        movableObject.xPosition = innerCounter;
                        movableObject.yPosition = outerCounter;
                        ++this.objectsCounter;
                        return;
                    } else {
                        --position;
                    }
                }
            }
        }
    }
    private void putAtRandomPosition(GameObject object) {
        int position = new Random().nextInt(this.infoStore.length * this.infoStore[0].length + 1 - this.objectsCounter);
        for (int outerCounter = 0; outerCounter < infoStore.length; ++outerCounter) {
            for (int innerCounter = 0; innerCounter < this.infoStore[0].length; ++innerCounter) {
                if (this.infoStore[outerCounter][innerCounter] == null) {
                    if (position == 0) {
                        this.infoStore[outerCounter][innerCounter] = object;
                        ++this.objectsCounter;
                        return;
                    } else {
                        --position;
                    }
                }
            }
        }
    }

    public void Update() throws GameOverException {
        UpdateMovable(player);
        this.paintMap();
        if(player.getPathfinder().isTarget()) {
            throw new GameOverException("You won!");
        }
        for (Enemy enemy: enemies) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(UpdateMovable(enemy)) {
                paintMap();
            }
        }
    }
    private boolean UpdateMovable(MovableObject movableObject) throws GameOverException {
        int xPosition = movableObject.xPosition;
        int yPosition = movableObject.yPosition;
        boolean result = movableObject.Update();
        infoStore[yPosition][xPosition] = null;
        infoStore[movableObject.yPosition][movableObject.xPosition] = movableObject;
        return result;
    }

    public int getX_Size() {
        return infoStore[0].length;
    }

    public int getY_Size() {
        return infoStore.length;
    }

    public void paintMap() {
        if (!applicationProperties.isDevMode) {
            System.out.println(colorize(Command.CLEAR_SCREEN()));
        }
        for (int y = 0; y < infoStore.length; ++y) {
            for (int x = 0; x < infoStore[0].length; ++x) {
                if (infoStore[y][x] != null) {
                    infoStore[y][x].draw();
                } else {
                    System.out.print(colorize(emptyChar, BLACK_TEXT(), emptyColor));
                }
            }
            System.out.print("\n");
        }
    }
}
