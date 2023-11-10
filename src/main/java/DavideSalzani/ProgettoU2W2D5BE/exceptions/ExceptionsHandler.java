package DavideSalzani.ProgettoU2W2D5BE.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsListDTO handleBadRequestException(BadRequestException ex){
        if (ex.getErrorsList() != null) {
            List<String> errorsList = ex.getErrorsList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsListDTO(ex.getMessage(),LocalDate.now(), errorsList);
        }else{
            return new ErrorsListDTO(ex.getMessage(), LocalDate.now(), new ArrayList<>());
        }
    }
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadDTO handleAlreadyExistException(AlreadyExistException ex){
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayloadDTO handleNotFoundException(NotFoundException ex){
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDate.now());
    }
}
