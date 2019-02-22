package crypto.org.services;

import crypto.org.api.v1.mapper.CustomerMapper;
import crypto.org.api.v1.model.CustomerDTO;
import crypto.org.domain.Customer;
import crypto.org.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerDTO customerDTO = customerMapper
                .customerToCustomerDTO(customerRepository.findById(id).orElseThrow(RuntimeException::new));
                customerDTO.setCustomerUrl("/api/v1/customer/" + id);

                return customerDTO;
    }

//    @Override
//    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
//        Customer customer = customerMapper.customerDtotoCustomer(customerDTO);
//
//        Customer savedCustomer = customerRepository.save(customer);
//
//        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
//
//        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
//
//        return returnDto;
//    }
//
//    @Override
//    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
//
//        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
//
//        customer.setFirstName(customerDTO.getFirstName());
//        customer.setLastName(customerDTO.getLastName());
//
//        customerRepository.save(customer);
//
//        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customer);
//        returnDto.setCustomerUrl("/api/v1/customer/" + id);
//
//        return returnDto;
//    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDtotoCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtotoCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if (customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }
        CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        returnedDTO.setCustomerUrl("/api/v1/customer/" + returnedDTO.getId());

            return returnedDTO;
        }).orElseThrow(RuntimeException::new); //todo implement better exception handling
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);

    }
}
