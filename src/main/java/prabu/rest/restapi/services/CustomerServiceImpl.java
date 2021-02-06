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
        return  saveAndReturnCustomerDTO(customerMapper.customerDTOtoCustomer(customerDTO));

    }

    private CustomerDTO saveAndReturnCustomerDTO(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl("/api/v1/customer/" + returnDTO.getId());
        return returnDTO;
    }

    @Override
    public CustomerDTO updateCustomerById(Long id, CustomerDTO customerDTO) {
        Customer updateCustomer = customerMapper.customerDTOtoCustomer(customerDTO);
        updateCustomer.setId(id);
        return saveAndReturnCustomerDTO(updateCustomer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }

            CustomerDTO returnCustomer = customerMapper.customerToCustomerDTO(customerRepository.save(customer));

            returnCustomer.setCustomerUrl("/api/v1/customer/" + id);

            return returnCustomer;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
