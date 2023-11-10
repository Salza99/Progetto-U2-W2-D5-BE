package DavideSalzani.ProgettoU2W2D5BE.exceptions;

public class UnderMaintenanceException extends RuntimeException{
    public UnderMaintenanceException(long id){
        super("il dispositivo con id: " + id + " è in manutenzione e non può quindi essere assegnato ad alcun utente");
    }
}
