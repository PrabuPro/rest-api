package prabu.rest.restapi.services;

import org.springframework.stereotype.Service;
import prabu.rest.restapi.api.vi.mapper.CategoryMapper;
import prabu.rest.restapi.api.vi.model.CategoryDTO;
import prabu.rest.restapi.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                                    .stream()
                                    .map(categoryMapper::categoryToCategoryDTO)
                                    .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
