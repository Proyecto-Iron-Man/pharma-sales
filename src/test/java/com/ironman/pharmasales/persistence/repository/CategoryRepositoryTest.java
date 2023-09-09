package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void canRegisterCategory(){
        Category category = Category.builder()
                .name("Categoria")
                .state(State.ACTIVE.getValue())
                .build();

        Category categoryCreated = categoryRepository.save(category);

        log.info("canRegisterCategory: " + categoryCreated);

        assertNotNull(categoryCreated);
        assertNotNull(categoryCreated.getId());
    }

    @Test
    void canUpdateCategory(){
        Category category = Category.builder()
                .name("Categoria")
                .state(State.ACTIVE.getValue())
                .build();

        Category categoryCreated = categoryRepository.save(category);

        Category categoryEdit = Category.builder()
                .id(categoryCreated.getId())
                .name("Categoria Editado")
                .state(State.ACTIVE.getValue())
                .build();


        Category categorySaved = categoryRepository.save(categoryEdit);
        log.info("canUpdateCategory: "+ categorySaved);

        assertNotNull(categorySaved);
        assertEquals(categoryCreated.getId(), categorySaved.getId());
        assertEquals("Categoria Editado", categorySaved.getName());
    }

    @Test
    void canFoundByIdCategory() {
        Category category = Category.builder()
                .name("Categoria")
                .state(State.ACTIVE.getValue())
                .build();

        Category categorySaved = categoryRepository.save(category);

        Category category2 = Category.builder()
                .name("Categoria Nueva")
                .state(State.ACTIVE.getValue())
                .build();

        Category categorySaved2 = categoryRepository.save(category2);

        Category categoryFound = categoryRepository.findById(category.getId()).get();

        assertEquals(categorySaved.getName(), categoryFound.getName());

    }

}