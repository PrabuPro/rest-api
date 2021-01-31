package prabu.rest.restapi.services;

import prabu.rest.restapi.api.vi.model.CategoryDTO;
import prabu.rest.restapi.domain.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

}
