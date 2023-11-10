package DavideSalzani.ProgettoU2W2D5BE.devices.deviceDTO;

import DavideSalzani.ProgettoU2W2D5BE.devices.TypeOfDevice;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record NewDeviceDTO(
        @NotEmpty(message = "inserire un tipo di dispositivo (laptop, tablet, smartphone)")
        @Pattern(regexp = "^(?i)(smartphone|tablet|laptop)$", message = "il valore del campo type può essere solo o 'smartphone' o 'tablet' o 'laptop'")
        String type
) {
}
