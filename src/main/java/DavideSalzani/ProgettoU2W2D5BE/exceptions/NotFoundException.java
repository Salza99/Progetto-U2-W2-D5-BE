package DavideSalzani.ProgettoU2W2D5BE.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String whichRecord){
        super(whichRecord + " non presente a database!");
    }
}
