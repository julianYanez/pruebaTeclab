package ar.edu.teclab.prueba.exceptions;

public class CarreraException extends Exception {
    private String message;
    private String code;

    public CarreraException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
