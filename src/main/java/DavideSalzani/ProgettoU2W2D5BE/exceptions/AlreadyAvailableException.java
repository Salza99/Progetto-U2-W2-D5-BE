package DavideSalzani.ProgettoU2W2D5BE.exceptions;

public class AlreadyAvailableException extends RuntimeException{
    public AlreadyAvailableException(long id) {
        super("il dispositivo con id: " + id + " è già disponibile");
    }
}
