package org.kalum.core.utils;

public class KalumException extends Exception {

    private String message;
    private int codigoError;

    public KalumException() {

    }

    public KalumException(int codigoError) {
        this.codigoError = codigoError;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        if (codigoError == 50301) {
            this.message = "Error al tratar de conectarse a la base de datos";
        } else if (codigoError == 50302) {
            this.message = "Error al momento de obtener la información de la base de datos";
        } else if (codigoError == 50303) {
            this.message = "Error al momento de guardar la información en la base de datos";
        }
        return message;
    }
}
