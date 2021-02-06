package prabu.rest.restapi.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.api.vi.model.VendorListDTO;
import prabu.rest.restapi.services.VendorService;

@Controller
@RequestMapping(VendorController.VENDOR_URL)
public class VendorController {

    public static final String VENDOR_URL = "/api/v1/vendor";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @GetMapping
    public ResponseEntity<VendorListDTO> getVendors(){
        return new ResponseEntity<VendorListDTO>(
                new VendorListDTO(vendorService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long id){
        return new ResponseEntity<VendorDTO>(vendorService.findById(id), HttpStatus.OK);
    }

}
