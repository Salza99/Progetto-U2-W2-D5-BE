package DavideSalzani.ProgettoU2W2D5BE.devices;

import DavideSalzani.ProgettoU2W2D5BE.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepo extends JpaRepository<Device, Long> {
    @Query("SELECT d.assignedTo FROM Device d WHERE d.id = :id")
    User findAssignedUserByDeviceId(@Param("id") long id);
}
