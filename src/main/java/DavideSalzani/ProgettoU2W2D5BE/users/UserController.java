package DavideSalzani.ProgettoU2W2D5BE.users;

import DavideSalzani.ProgettoU2W2D5BE.exceptions.BadRequestException;
import DavideSalzani.ProgettoU2W2D5BE.users.userDTO.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public long createUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return userService.save(body);
        }

    }
    @GetMapping("")
    public Page<User> getPaginatedUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "name") String orderBy){
        return userService.getAll(page,size,orderBy);
    }
}
