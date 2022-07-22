package edu.school42;

public class GameOverException extends Throwable {
    String message;
    {
        message = "Game is over";
    }
    public  GameOverException() {}
    public  GameOverException(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}