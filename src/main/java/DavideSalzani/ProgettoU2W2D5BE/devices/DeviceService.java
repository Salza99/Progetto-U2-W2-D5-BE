package DavideSalzani.ProgettoU2W2D5BE.devices;

import DavideSalzani.ProgettoU2W2D5BE.devices.deviceDTO.NewDeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeviceService {
    @Autowired
    DeviceRepo deviceRepo;

    public long createDevice(NewDeviceDTO body){

            Device d = new Device();
        if (Objects.equals(body.type().toLowerCase(), TypeOfDevice.smartphone.name())) {
            d.setType(TypeOfDevice.smartphone);
        } else if (Objects.equals(body.type().toLowerCase(), TypeOfDevice.tablet.name())) {
            d.setType(TypeOfDevice.tablet);
        } else if (Objects.equals(body.type().toLowerCase(), TypeOfDevice.laptop.name())){
            d.setType(TypeOfDevice.laptop);
        }
        d.setDeviceStatus(Conditions.disponibile);
            deviceRepo.save(d);
            return d.getId();

    }
}
