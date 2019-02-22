package crypto.org.controllers.v1;


import crypto.org.api.v1.model.CustomerDTO;
import crypto.org.api.v1.model.CustomerListDTO;
import crypto.org.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomersById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO), HttpStatus.CREATED); //STATUS 201 CREATED

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }

}
