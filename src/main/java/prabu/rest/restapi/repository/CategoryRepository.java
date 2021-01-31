package prabu.rest.restapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import prabu.rest.restapi.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String anyString);
}
