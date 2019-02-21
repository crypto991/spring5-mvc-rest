package crypto.org.bootstrap;

import crypto.org.domain.Category;
import crypto.org.domain.Customer;
import crypto.org.repositories.CategoryRepository;
import crypto.org.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
public class DataLoader implements CommandLineRunner {


    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        loadCategories();

        loadCustomers();

    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Milos");
        customer1.setLastName("Cuic");

        Customer customer2 = new Customer();
        customer2.setFirstName("Djordje");
        customer2.setLastName("Perovic");

        customerRepository.save(customer1);
        customerRepository.save(customer2);


        System.out.println("Data Loader = " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        dried.setName("Fresh");

        Category exotic = new Category();
        dried.setName("Exotic");

        Category nuts = new Category();
        dried.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loader = " + categoryRepository.count());
    }
}
