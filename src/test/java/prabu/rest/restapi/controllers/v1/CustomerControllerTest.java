package prabu.rest.restapi.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import prabu.rest.restapi.api.vi.model.CustomerDTO;
import prabu.rest.restapi.domain.Customer;
import prabu.rest.restapi.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static prabu.rest.restapi.controllers.v1.AbstractRestController.asJsonString;

class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getCustomerList() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(1L);
        customerDTO1.setFirstName("John");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(2L);
        customerDTO2.setFirstName("John");

        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.getCustomerList()).thenReturn(customerDTOList);

        mockMvc.perform(get("/api/v1/customer/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    void getCustomerByFirstName() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("john");

        when(customerService.getCustomerByName(anyString())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customer/john")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("john")));

    }

    @Test
    void saveNewCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("john");

        CustomerDTO returnCustomer = new CustomerDTO();
        returnCustomer.setId(customer.getId());
        returnCustomer.setFirstName(customer.getFirstName());
        returnCustomer.setCustomerUrl("/api/v1/customer/1");

        when(customerService.saveNewCustomer(customer)).thenReturn(returnCustomer);

        mockMvc.perform(post("/api/v1/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("john")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customer/1")));
    }
}