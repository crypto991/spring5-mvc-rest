package crypto.org.services;

import crypto.org.api.v1.mapper.CustomerMapper;
import crypto.org.api.v1.model.CustomerDTO;
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


}
