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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VendorServiceImplTest {

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
}