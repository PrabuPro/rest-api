package prabu.rest.restapi.controllers.v1;


import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import prabu.rest.restapi.api.vi.model.CustomerDTO;
import prabu.rest.restapi.api.vi.model.CustomerListDTO;
import prabu.rest.restapi.services.CustomerService;

@Controller
@RequestMapping("api/v1/customer/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<CustomerListDTO> getCustomerList(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getCustomerList()), HttpStatus.OK);
    }

    @GetMapping("{firstName}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstName){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerByName(firstName), HttpStatus.OK);
    }

}
