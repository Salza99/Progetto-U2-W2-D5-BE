package DavideSalzani.ProgettoU2W2D5BE.devices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends JpaRepository<Device, Long> {
}
