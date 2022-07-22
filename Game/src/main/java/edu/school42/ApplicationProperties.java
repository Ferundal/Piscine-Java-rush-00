package edu.school42;

import com.diogonunes.jcolor.Attribute;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        String filePath = "Game/resources/application-production.properties";
        FileReader applicationPropertiesFileReader;

        try {
            applicationPropertiesFileReader = new FileReader(filePath);
        }
        catch (java.io.FileNotFoundException fileNotFoundException) {
            throw new IllegalParametersException();
        }
        BufferedReader applicationPropertiesBufferedReader = new BufferedReader(applicationPropertiesFileReader);
        ApplicationProperties applicationProperties = new ApplicationProperties();
        if (profile.equals("dev")) {
            applicationProperties.isDevMode = true;
        }
        applicationProperties.enemyChar = parseChar("enemy.char", applicationPropertiesBufferedReader);
        applicationProperties.playerChar = parseChar("player.char", applicationPropertiesBufferedReader);
        applicationProperties.wallChar = parseChar("wall.char", applicationPropertiesBufferedReader);
        applicationProperties.goalChar = parseChar("goal.char", applicationPropertiesBufferedReader);
        applicationProperties.emptyChar = parseChar("empty.char", applicationPropertiesBufferedReader);
        applicationProperties.enemyColor = parseColor("enemy.color", applicationPropertiesBufferedReader);
        applicationProperties.playerColor = parseColor("player.color", applicationPropertiesBufferedReader);
        applicationProperties.wallColor = parseColor("wall.color", applicationPropertiesBufferedReader);
        applicationProperties.goalColor = parseColor("goal.color", applicationPropertiesBufferedReader);
        applicationProperties.emptyColor = parseColor("empty.color", applicationPropertiesBufferedReader);
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
