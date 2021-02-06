package prabu.rest.restapi.services;

import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.api.vi.model.VendorListDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> findAll();

    VendorDTO findById(Long id);

    VendorDTO saveVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    void deleteVendor(Long id);
}
