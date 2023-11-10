package DavideSalzani.ProgettoU2W2D5BE.users;

import DavideSalzani.ProgettoU2W2D5BE.exceptions.AlreadyExistException;
import DavideSalzani.ProgettoU2W2D5BE.exceptions.BadRequestException;
import DavideSalzani.ProgettoU2W2D5BE.exceptions.NotFoundException;
import DavideSalzani.ProgettoU2W2D5BE.users.userDTO.ChangeUserEmailDTO;
import DavideSalzani.ProgettoU2W2D5BE.users.userDTO.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public long save(NewUserDTO body){
        User u = new User();
        if (userRepo.findByUsername(body.username()).isPresent()) {
            throw new AlreadyExistException(body.username());
        } else if (userRepo.findByEmail(body.email()).isPresent()) {
            throw new AlreadyExistException(body.email());
        }else {
            u.setEmail(body.email());
            u.setName(body.name());
            u.setSurname(body.surname());
            u.setUsername(body.username());
            userRepo.save(u);
            return u.getId();
        }
    }
    public Page<User> getAll(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userRepo.findAll(pageable);
    }
    public User getSingleUser(long id){
        return userRepo.findById(id).orElseThrow(()-> new NotFoundException("User"));
    }
    public User patchOnEmail(ChangeUserEmailDTO changeUserMail, long id){
        User found = userRepo.findById(id).orElseThrow(()-> new NotFoundException("User"));
        if (Objects.equals(found.getEmail().toLowerCase(), changeUserMail.email().toLowerCase().trim())) {
            throw new BadRequestException("inserisci una mail diversa da quella precedente");
        } else if (userRepo.findByEmail(changeUserMail.email()).isPresent()) {
            throw new AlreadyExistException(changeUserMail.email());
        }else {
            found.setEmail(changeUserMail.email());
            userRepo.save(found);
            return found;
        }
    }
    public void deleteUser(long id){
       User toRemove = userRepo.findById(id).orElseThrow(()-> new NotFoundException("User"));
        userRepo.delete(toRemove);
    }
}
