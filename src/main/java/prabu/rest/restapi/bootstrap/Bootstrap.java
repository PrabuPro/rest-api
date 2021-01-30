package prabu.rest.restapi.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import prabu.rest.restapi.domain.Category;
import prabu.rest.restapi.repository.CategoryRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
}
