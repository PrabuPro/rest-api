package prabu.rest.restapi.controllers.v1;


import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("id/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> saveNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.saveNewCustomer(customerDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.updateCustomerById(Long.valueOf(id), customerDTO), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.patchCustomer(Long.valueOf(id), customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
