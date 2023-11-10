package DavideSalzani.ProgettoU2W2D5BE.devices;

import DavideSalzani.ProgettoU2W2D5BE.devices.deviceDTO.ChangeStatusInMantainanceOrDismissDTO;
import DavideSalzani.ProgettoU2W2D5BE.devices.deviceDTO.NewDeviceDTO;
import DavideSalzani.ProgettoU2W2D5BE.exceptions.DismissDeviceException;
import DavideSalzani.ProgettoU2W2D5BE.exceptions.NotFoundException;
import DavideSalzani.ProgettoU2W2D5BE.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public Device getSingle(long id){
       return deviceRepo.findById(id).orElseThrow(() -> new NotFoundException("Dispositivo"));
    }
    public Device underMaintenanceOrDismiss(ChangeStatusInMantainanceOrDismissDTO body, long id){
        Device found = deviceRepo.findById(id).orElseThrow(()-> new NotFoundException("dispositivo"));
        if (found.getAssignedTo() != null) {
            User u = deviceRepo.findAssignedUserByDeviceId(id);
            List<Device> newListForUser = new ArrayList<>(u.getAssignedCompanyDevices());
            newListForUser.remove(found);
            u.setAssignedCompanyDevices(newListForUser);
            if (Objects.equals(body.status().toLowerCase().trim(), Conditions.in_manutenzione.name()) && found.getDeviceStatus() != Conditions.in_manutenzione && found.getDeviceStatus() != Conditions.dismesso) {
                found.setDeviceStatus(Conditions.in_manutenzione);
                deviceRepo.save(found);
                return found;
            }else if (Objects.equals(body.status().toLowerCase().trim(), Conditions.dismesso.name()) && found.getDeviceStatus() != Conditions.dismesso) {
                found.setDeviceStatus(Conditions.dismesso);
                deviceRepo.save(found);
                return found;
            }
            else {
                throw new DismissDeviceException(id);
            }
        } else {
            if (Objects.equals(body.status().toLowerCase().trim(), Conditions.in_manutenzione.name()) && found.getDeviceStatus() != Conditions.in_manutenzione && found.getDeviceStatus() != Conditions.dismesso) {
                found.setDeviceStatus(Conditions.in_manutenzione);
                deviceRepo.save(found);
                return found;
            }else if (Objects.equals(body.status().toLowerCase().trim(), Conditions.dismesso.name()) && found.getDeviceStatus() != Conditions.dismesso) {
                found.setDeviceStatus(Conditions.dismesso);
                deviceRepo.save(found);
                return found;
            }else {
                throw new DismissDeviceException(id);
            }
        }
    }
}
