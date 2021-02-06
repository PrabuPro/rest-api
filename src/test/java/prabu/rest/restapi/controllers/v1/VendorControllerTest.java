package prabu.rest.restapi.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.domain.Vendor;
import prabu.rest.restapi.services.VendorService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(RestResponceEntityExceptoinHandler.class)
                .build();
    }

    @Test
    void getVendors() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName("vendor1");

        VendorDTO vendor2 = new VendorDTO();
        vendor2.setName("vendor2");

        List<VendorDTO> vendorDTOList = Arrays.asList(vendor1, vendor2);

        when(vendorService.findAll()).thenReturn(vendorDTOList);

        mockMvc.perform(get("/api/v1/vendor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));


    }
}