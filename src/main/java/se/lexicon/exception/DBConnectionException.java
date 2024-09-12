package se.lexicon.exception;

public class DBConnectionException extends RuntimeException{

    public DBConnectionException(String message) {
        super(message);
    }

}
