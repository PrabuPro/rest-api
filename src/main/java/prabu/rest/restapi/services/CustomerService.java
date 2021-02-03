package prabu.rest.restapi.services;

import prabu.rest.restapi.api.vi.model.CustomerDTO;
import prabu.rest.restapi.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomerList();

    CustomerDTO getCustomerByName(String firstName);

    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

}
