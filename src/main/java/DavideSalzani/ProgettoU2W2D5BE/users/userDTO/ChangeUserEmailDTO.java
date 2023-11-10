package DavideSalzani.ProgettoU2W2D5BE.users.userDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangeUserEmailDTO(
        @NotEmpty(message = "campo email non può essere lasciato vuoto!")
        @jakarta.validation.constraints.Email(regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}", message = "inserire una mail valida!")
        String email
) {
}
