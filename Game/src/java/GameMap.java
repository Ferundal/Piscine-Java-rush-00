import java.util.ArrayList;
import java.util.Random;

public class GameMap implements Map {
    int objectsCounter = 0;
    private GameObject [][] infoStore;
    private GameMap() {}
    public GameMap(int size) throws IllegalParametersException {
        Integer objectCounter = 0;
        if (size < 2) {
            throw new IllegalParametersException();
        }
        this.infoStore = new GameObject [size][size];
    }
    public void putAtRandomPosition(GameObject object) throws IllegalParametersException {
        if (this.objectsCounter >= infoStore.length * infoStore[0].length) {
            throw new IllegalParametersException();
        }
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
    void paintMap() {
        for (int x = 0; x < infoStore[0].length; x ++) {
            for (int y = infoStore.length - 1; y >= 0; --y) {
                if (infoStore[y][x] != null) {
                    infoStore[y][x].print();
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
    @Override
    public boolean moveUp() {
        return false;
    }

    @Override
    public boolean moveDown() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        return false;
    }

    @Override
    public boolean moveRight() {
        return false;
    }

    @Override
    public boolean isTarget() {
        return false;
    }
}
