package crypto.org.controllers;

import crypto.org.api.v1.model.CustomerDTO;
import crypto.org.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {


    public static final long ID = 1L;
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

//        categoryController = new CategoryController(categoryService);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomer() throws Exception{
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Miroljub");
        customer1.setLastName("Petrovic");
        customer1.setId(1L);
        customer1.setCustomerUrl("/api/v1/customer/1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Piksi");
        customer2.setLastName("Radonjic");
        customer2.setId(2L);
        customer2.setCustomerUrl("/api/v1/customer/2");


        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDTOList", hasSize(2)));
    }

    @Test
    public void testGetCustomerById () throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID);
        customer1.setFirstName("Djoka");
        customer1.setLastName("Petrovic");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }


    @Test
    public void testGetCustomerByNameAndID() throws Exception {

        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customer1.setCustomerUrl("/api/v1/customer/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        //when
        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Michale")));
    }
}