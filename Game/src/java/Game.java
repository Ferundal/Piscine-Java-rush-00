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
        Object player = new Object();
        Object enemy = new Object();
        Object target = new Object();
        Object wall = new Object();
        GameMap gameMap = new GameMap(size);
        gameMap.paintMap();
    }

}