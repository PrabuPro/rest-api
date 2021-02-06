package prabu.rest.restapi.services;

import prabu.rest.restapi.api.vi.model.CustomerDTO;
import prabu.rest.restapi.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomerList();

    CustomerDTO getCustomerByName(String firstName);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomerById(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id,CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
