package edu.school42;

import com.diogonunes.jcolor.Attribute;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class ApplicationProperties {
    public boolean isDevMode;
    public String enemyChar;
    public String playerChar;
    public String wallChar;
    public String goalChar;
    public String emptyChar;
    public Attribute enemyColor;
    public Attribute playerColor;
    public Attribute wallColor;
    public Attribute goalColor;
    public Attribute emptyColor;
    {
        isDevMode = false;
    }
    private ApplicationProperties() {}

    public static ApplicationProperties loadFromFile(String profile) throws IllegalParametersException {
        boolean isDevMode = false;
        if (profile.equals("dev")) {
            System.out.println("Developer mode is on.");
            isDevMode = true;
        }
        String fileName = "application-" + profile + ".properties";
        InputStream in;
        BufferedReader reader;
        in = ApplicationProperties.class.getResourceAsStream("/" + fileName);
        reader = new BufferedReader(new InputStreamReader(in));
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.isDevMode = isDevMode;
        applicationProperties.enemyChar = parseChar("enemy.char", reader);
        applicationProperties.playerChar = parseChar("player.char", reader);
        applicationProperties.wallChar = parseChar("wall.char", reader);
        applicationProperties.goalChar = parseChar("goal.char", reader);
        applicationProperties.emptyChar = parseChar("empty.char", reader);
        applicationProperties.enemyColor = parseColor("enemy.color", reader);
        applicationProperties.playerColor = parseColor("player.color", reader);
        applicationProperties.wallColor = parseColor("wall.color", reader);
        applicationProperties.goalColor = parseColor("goal.color", reader);
        applicationProperties.emptyColor = parseColor("empty.color", reader);
        if (isDevMode) {
            System.out.println("Application Properties loaded from " + fileName +".");
        }
        return applicationProperties;
    }
    private static String parseChar(String propertyName, BufferedReader bufferedReader) throws IllegalParametersException {
        String[] splitString = getNextProperties(bufferedReader);
        if ((splitString.length < 1) ||
                !splitString[0].equals(propertyName)) {
            throw new IllegalParametersException();
        }
        if (splitString.length == 1) {
            return new String(" ");
        }
        return splitString[1];
    }

    private static Attribute parseColor(String propertyName, BufferedReader bufferedReader) throws IllegalParametersException {
        String[] splitString = getNextProperties(bufferedReader);
        if (splitString.length != 2 ||
                !splitString[0].equals(propertyName)) {
            throw new IllegalParametersException();
        }
        Attribute color = stringToColor(splitString[1]);
        if (color == null) {
            throw new IllegalParametersException();
        }
        return color;
    }

    private static String[] getNextProperties(BufferedReader bufferedReader) throws IllegalParametersException {
        String parseString;
        try {
            parseString = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (parseString == null) {
            throw new IllegalParametersException();
        }
        return parseString.split(" *= *", 0);
    }

    private static Attribute stringToColor(String color) {
        switch (color) {
            case "WHITE":
                return Attribute.WHITE_BACK();
            case "BLACK":
                return Attribute.BLACK_BACK();
            case "BLUE":
                return Attribute.BLUE_BACK();
            case "CYAN":
                return Attribute.CYAN_BACK();
            case "GREEN":
                return Attribute.GREEN_BACK();
            case "MAGENTA":
                return Attribute.MAGENTA_BACK();
            case "RED":
                return Attribute.RED_BACK();
            case "YELLOW":
                return Attribute.YELLOW_BACK();
        }
        return null;
    }

}
