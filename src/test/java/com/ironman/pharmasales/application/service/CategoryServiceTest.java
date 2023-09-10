package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.category.CategoryDto;
import com.ironman.pharmasales.application.dto.category.mapper.CategoryMapper;
import com.ironman.pharmasales.application.service.impl.CategoryServiceImpl;
import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.persistence.repository.CategoryRepository;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    private CategoryService categoryService;
    private AutoCloseable closeable;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void whenValidId_thenCategoryShouldBeFound() throws DataNotFoundException {
        // given
        Long id = 1L;

        Category category = Category.builder()
                .id(id)
                .name("Categoria")
                .state(State.ACTIVE.getValue())
                .build();

        Mockito.when(categoryRepository.findById(id))
                .thenReturn(Optional.of(category));


        // when
        CategoryDto categoryDto = categoryService.findById(id);
        log.info("whenValidId_thenCategoryShouldBeFound: " + categoryDto);

        // then
        assertEquals(category.getName(), categoryDto.getName());
    }

    @Test
    void whenNotValidId_thenExceptionResult() {
        // given
        Long id = 1L;
        String expectedMessage = "Categoria no encontrado para el id: " + id;

        // when
        Exception exception = assertThrows(DataNotFoundException.class, () -> categoryService.findById(id));
        String actualMessage = exception.getMessage();

        log.info("whenNotValidId_thenException: " + exception);

        // then
        assertEquals(expectedMessage, actualMessage);
    }

}