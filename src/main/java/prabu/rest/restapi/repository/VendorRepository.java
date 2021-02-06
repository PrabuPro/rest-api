package prabu.rest.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prabu.rest.restapi.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
