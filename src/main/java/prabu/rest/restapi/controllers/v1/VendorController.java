package prabu.rest.restapi.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.api.vi.model.VendorListDTO;
import prabu.rest.restapi.services.VendorService;

@RestController
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

    @PostMapping()
    public ResponseEntity<VendorDTO> saveVendor(@RequestBody VendorDTO vendorDTO){
        return new ResponseEntity<VendorDTO>(
                vendorService.saveVendor(vendorDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendorById(@PathVariable Long id,@RequestBody VendorDTO vendorDTO){
        return new ResponseEntity<VendorDTO>(
                vendorService.updateVendor(id,vendorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendorById(@PathVariable Long id){
        vendorService.deleteVendor(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
