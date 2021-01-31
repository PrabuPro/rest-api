package prabu.rest.restapi.api.vi.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prabu.rest.restapi.api.vi.model.CategoryDTO;
import prabu.rest.restapi.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}