package prabu.rest.restapi.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.ResourceAccessException;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.domain.Vendor;
import prabu.rest.restapi.services.ResourceNotFoundException;
import prabu.rest.restapi.services.VendorService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static prabu.rest.restapi.controllers.v1.AbstractRestController.asJsonString;

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

        mockMvc.perform(get(VendorController.VENDOR_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    void getVendorById() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("vendor1");

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());
        vendorDTO.setVendorUrl(VendorController.VENDOR_URL + "/" + vendor.getId());

        when(vendorService.findById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(VendorController.VENDOR_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendor.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.VENDOR_URL + "/1")));
    }

    @Test
    void getVendorByIdNotFound() throws Exception {
        when(vendorService.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VendorController.VENDOR_URL + "/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveVendor() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName("vendor3");

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("vendor3");
        vendorDTO.setVendorUrl(VendorController.VENDOR_URL + "/3");

        when(vendorService.saveVendor(any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(post(VendorController.VENDOR_URL )
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("vendor3")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.VENDOR_URL + "/3")));
    }

    @Test
    void updateVendorById() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName("vendor3");

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("vendor311");
        vendorDTO.setVendorUrl(VendorController.VENDOR_URL + "/3");

        when(vendorService.updateVendor(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(put(VendorController.VENDOR_URL + "/3" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("vendor311")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.VENDOR_URL + "/3")));
    }
}