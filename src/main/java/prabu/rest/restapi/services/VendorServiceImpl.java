package prabu.rest.restapi.services;

import org.springframework.stereotype.Service;
import prabu.rest.restapi.api.vi.mapper.VendorMapper;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.api.vi.model.VendorListDTO;
import prabu.rest.restapi.controllers.v1.VendorController;
import prabu.rest.restapi.domain.Vendor;
import prabu.rest.restapi.repository.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> findAll() {
        return vendorRepository.findAll().stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(VendorController.VENDOR_URL + "/" + vendor.getId());
                    return vendorDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO findById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(VendorController.VENDOR_URL + "/" + id);
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO saveVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.ventorDTOToVendor(vendorDTO);

        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnVendor = vendorMapper.vendorToVendorDTO(savedVendor) ;
        returnVendor.setVendorUrl(VendorController.VENDOR_URL + "/" + savedVendor.getId());

        return returnVendor;
    }
}
