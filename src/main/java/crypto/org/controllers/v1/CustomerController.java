package crypto.org.controllers.v1;


import crypto.org.api.v1.model.CustomerDTO;
import crypto.org.api.v1.model.CustomerListDTO;
import crypto.org.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getListOfCustomers(){

        return new CustomerListDTO(customerService.getAllCustomers());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomersById(@PathVariable Long id){

        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
         return customerService.createNewCustomer(customerDTO);
         //STATUS 201 CREATED

    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id){

        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updatePathCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id){

        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);

    }




}
