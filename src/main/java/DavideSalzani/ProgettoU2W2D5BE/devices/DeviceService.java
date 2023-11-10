package DavideSalzani.ProgettoU2W2D5BE.devices;

import DavideSalzani.ProgettoU2W2D5BE.devices.deviceDTO.NewDeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Device> getAll(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return deviceRepo.findAll(pageable);
    }
}
