package DavideSalzani.ProgettoU2W2D5BE.exceptions;

import java.time.LocalDate;

public record ErrorsPayloadDTO(
        String message,
        LocalDate timeStamp
) {
}
