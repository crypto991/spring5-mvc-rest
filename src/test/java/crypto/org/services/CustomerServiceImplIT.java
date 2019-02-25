package crypto.org.services;


import crypto.org.api.v1.mapper.CustomerMapper;
import crypto.org.api.v1.model.CustomerDTO;
import crypto.org.bootstrap.DataLoader;
import crypto.org.domain.Customer;
import crypto.org.repositories.CategoryRepository;
import crypto.org.repositories.CustomerRepository;
import crypto.org.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;


    @Before
    public void setUp() throws Exception{
        System.out.println("Loading Customers Data");
        System.out.println(customerRepository.findAll().size());

        //setup data for testing
        DataLoader dataLoader = new DataLoader(categoryRepository, customerRepository,vendorRepository );
        dataLoader.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception {
        String updateName = "UpdateName";
        long id = getCustomerUpdateIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        //save original first name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updateName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updateName, updatedCustomer.getFirstName());
        assertThat(originalFirstName,not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception {
        String updateName = "UpdateName";
        long id = getCustomerUpdateIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        //save original first / lastname
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updateName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updateName, updatedCustomer.getLastName());
        assertThat(originalFirstName,equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));

    }

    private Long getCustomerUpdateIdValue(){
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers Found: " + customers.size());

        //return list
        return customers.get(0).getId();
    }


}
