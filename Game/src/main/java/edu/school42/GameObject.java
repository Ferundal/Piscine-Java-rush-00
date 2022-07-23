package edu.school42;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class GameObject {
    private String displayedSymbol;
    private Attribute color;

    public GameObject(String displayedSymbol, Attribute color) {
        this.displayedSymbol = displayedSymbol;
        this.color = color;
    }
    public void draw() {
        System.out.print(colorize(displayedSymbol, BLACK_TEXT(), color));
    }
}
