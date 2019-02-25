package crypto.org.bootstrap;

import crypto.org.domain.Category;
import crypto.org.domain.Customer;
import crypto.org.domain.Vendor;
import crypto.org.repositories.CategoryRepository;
import crypto.org.repositories.CustomerRepository;
import crypto.org.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
public class DataLoader implements CommandLineRunner {


    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public DataLoader(CategoryRepository categoryRepository,
                      CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        loadCategories();

        loadCustomers();

        loadVendors();


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

    private void loadVendors(){
        Vendor vendor1 = new Vendor();
        vendor1.setName("Some Fruits From Serbia Ltd");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Cocaine From Columbia Ltd");


        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);

        System.out.println("Data Loader = " + vendorRepository.count());

    }
}
