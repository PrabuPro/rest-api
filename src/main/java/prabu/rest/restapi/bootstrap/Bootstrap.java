package prabu.rest.restapi.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import prabu.rest.restapi.domain.Category;
import prabu.rest.restapi.domain.Customer;
import prabu.rest.restapi.domain.Vendor;
import prabu.rest.restapi.repository.CategoryRepository;
import prabu.rest.restapi.repository.CustomerRepository;
import prabu.rest.restapi.repository.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        getCategories();
        getCustomers();
        getVendors();

    }

    private void getVendors() {
        Vendor vendor = new Vendor();
        vendor.setName("vendor1");

        Vendor vendor2 = new Vendor();
        vendor2.setName("vendor2");

        vendorRepository.save(vendor);
        vendorRepository.save(vendor2);

    }

    private void getCategories() {
        Category fruits = new Category();
        fruits.setName("fruit");

        Category vegitable = new Category();
        vegitable.setName("vegitable");

        Category meat = new Category();
        meat.setName("meat");

        Category dairy = new Category();
        dairy.setName("dairy");

        categoryRepository.save(fruits);
        categoryRepository.save(vegitable);
        categoryRepository.save(meat);
        categoryRepository.save(dairy);

        System.out.println("bootstrap data " + categoryRepository.count());
    }

    private void getCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("john");
        customer1.setLastName("doe");

        Customer customer2 = new Customer();
        customer2.setFirstName("john1");
        customer2.setLastName("doe1");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }
}
