package prabu.rest.restapi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import prabu.rest.restapi.api.vi.mapper.CustomerMapper;
import prabu.rest.restapi.api.vi.model.CustomerDTO;
import prabu.rest.restapi.domain.Customer;
import prabu.rest.restapi.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    public static final String NAME = "John";
    public static final long ID = 1L;
    @Mock
    CustomerRepository customerRepository;

    CustomerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getCustomerList() {
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOS = service.getCustomerList();

        assertEquals(2, customerDTOS.size());
    }

    @Test
    void getCustomerByName() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);

        when(customerRepository.findByFirstName(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = service.getCustomerByName(NAME);

        assertEquals(customerDTO.getId(), ID);
        assertEquals(customerDTO.getFirstName(), NAME);
    }

    @Test
    void saveNewCustomer(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstName(NAME);

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO savedCustomer = service.saveNewCustomer(customerDTO);

        assertEquals(savedCustomer.getId(), ID);
        assertEquals(savedCustomer.getFirstName(), NAME);
    }

    @Test
    void updateCustomerById(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO savedCustomer = service.updateCustomerById(ID,customerDTO);

        assertEquals(savedCustomer.getId(), ID);
        assertEquals(savedCustomer.getFirstName(), NAME);
    }

    @Test
    public void deleteByCustomerId(){
        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository,times(1)).deleteById(anyLong());
    }
}