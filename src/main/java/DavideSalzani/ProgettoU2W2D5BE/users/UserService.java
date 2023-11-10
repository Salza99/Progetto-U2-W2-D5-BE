package DavideSalzani.ProgettoU2W2D5BE.users;

import DavideSalzani.ProgettoU2W2D5BE.exceptions.AlreadyExistException;
import DavideSalzani.ProgettoU2W2D5BE.exceptions.NotFoundException;
import DavideSalzani.ProgettoU2W2D5BE.users.userDTO.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
