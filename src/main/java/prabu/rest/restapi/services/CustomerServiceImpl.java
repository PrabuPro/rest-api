package prabu.rest.restapi.services;

import org.springframework.stereotype.Service;
import prabu.rest.restapi.api.vi.mapper.CustomerMapper;
import prabu.rest.restapi.api.vi.model.CustomerDTO;
import prabu.rest.restapi.domain.Customer;
import prabu.rest.restapi.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/"+ customerDTO.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByName(String firstName) {
        return customerMapper.customerToCustomerDTO(customerRepository.findByFirstName(firstName)) ;
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(customerMapper.customerDTOtoCustomer(customerDTO));

        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl("/api/v1/customer/" + returnDTO.getId());

        return returnDTO;
    }
}
