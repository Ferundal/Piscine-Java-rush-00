package edu.school42;

public class IllegalParametersException extends Throwable {
    @Override
    public String toString() {
        return "Wrong command-line parameters";
    }
}
