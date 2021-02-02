package prabu.rest.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prabu.rest.restapi.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String anyString);
}
