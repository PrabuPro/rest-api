package prabu.rest.restapi.services;

import prabu.rest.restapi.api.vi.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomerList();

    CustomerDTO getCustomerByName(String firstName);

}
