package prabu.rest.restapi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import prabu.rest.restapi.api.vi.mapper.VendorMapper;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.domain.Vendor;
import prabu.rest.restapi.repository.VendorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {

    public static final String NAME = "vendor3";
    public static final long ID = 3L;
    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void findAll() {
        List<Vendor> vendors = Arrays.asList(new Vendor(),new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOList = vendorService.findAll();

        assertEquals(2, vendorDTOList.size());

    }

    @Test
    void findById(){
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("vendor");

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO findVendor = vendorService.findById(1L);

        assertEquals(findVendor.getName(), vendor.getName());
    }

    @Test
    void saveObject(){
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());

        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO savedVendor = vendorService.saveVendor(vendorDTO);

        assertEquals(savedVendor.getName(), vendor.getName());
    }

    @Test
    void updateVendor(){

        Vendor vendorUpdated = new Vendor();
        vendorUpdated.setId(ID);
        vendorUpdated.setName("vendor100");

        VendorDTO updateVendor = new VendorDTO();
        updateVendor.setName("vendor100");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorUpdated);

        VendorDTO vendorDTO = vendorService.updateVendor(ID,updateVendor);

        assertEquals(vendorDTO.getName(), updateVendor.getName());

    }

    @Test
    void deveteVendorById(){
        vendorRepository.deleteById(1L);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}