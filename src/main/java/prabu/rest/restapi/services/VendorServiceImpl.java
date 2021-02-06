package prabu.rest.restapi.services;

import org.springframework.stereotype.Service;
import prabu.rest.restapi.api.vi.mapper.VendorMapper;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.api.vi.model.VendorListDTO;
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
                    vendorDTO.setVendorUrl("/api/v1/vendor/" + vendor.getId());
                    return vendorDTO;
                }).collect(Collectors.toList());
    }
}
