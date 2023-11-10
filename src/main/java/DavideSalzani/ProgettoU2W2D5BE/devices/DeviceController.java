package DavideSalzani.ProgettoU2W2D5BE.devices;

import DavideSalzani.ProgettoU2W2D5BE.devices.deviceDTO.NewDeviceDTO;
import DavideSalzani.ProgettoU2W2D5BE.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public long createDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return deviceService.createDevice(body);
        }
    }
}
