package DavideSalzani.ProgettoU2W2D5BE.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
@Getter
public class BadRequestException extends RuntimeException{
    List<ObjectError> errorsList;
    public BadRequestException(String message){
        super(message);
    }
    public BadRequestException(List<ObjectError> errorsList){this.errorsList=errorsList;}
}
