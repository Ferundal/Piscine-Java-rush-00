package edu.school42;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Game {
    @Parameter(names = "--enemiesCount")
    public int enemiesCount;
    @Parameter(names = "--wallsCount")
    public int wallsCount;
    @Parameter(names = "--size")
    public int size;
    @Parameter(names = "--profile")
    public String profile;
    public static void main(String[] args) throws IllegalParametersException {
        Game game = new Game();
        JCommander.newBuilder()
                .addObject(game)
                .build()
                .parse(args);
        game.run();
    }
    private void run() throws IllegalParametersException {
        ApplicationProperties applicationProperties = ApplicationProperties.loadFromFile(profile);
        Map map = Map.GenerateMap(size, enemiesCount, wallsCount, applicationProperties);
        map.paintMap();
        try {
            while (true) {
                map.Update();
            }
        } catch (GameOverException gameOverException) {
            map.paintMap();
            System.out.println(gameOverException.toString());
        }
    }
}