package crypto.org.services;

import crypto.org.api.v1.mapper.CustomerMapper;
import crypto.org.api.v1.model.CustomerDTO;
import crypto.org.domain.Customer;
import crypto.org.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception{

        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then

        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerById() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Michale");
        customer.setLastName("Weston");


        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

//        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));


        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        verify(customerRepository, times(1)).findById(anyLong());

        assertEquals(customer.getId(), customerDTO.getId());

//        assertEquals("Michale", customerDTO.getFirstname());


    }

    @Test
    public void createCustomerTest() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Milos");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setId(1L);

        //REPOZITORI PROVER SAVE CUSTOMERA
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        //PROVERA KREIRANJA CUSTOMERA
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        //PROVERA JEDNAKOSTI CUSTOMERA I CUSTOMER DTO
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        //PROVERA URLA
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());

    }

    @Test
    public void saveCustomerByDTO() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }
}