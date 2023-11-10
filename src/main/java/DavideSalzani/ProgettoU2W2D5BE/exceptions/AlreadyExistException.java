package DavideSalzani.ProgettoU2W2D5BE.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String whichProperty){
        super(whichProperty + " già presente a database!");
    }
}
