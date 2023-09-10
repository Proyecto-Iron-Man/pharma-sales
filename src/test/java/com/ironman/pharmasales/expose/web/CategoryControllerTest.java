package com.ironman.pharmasales.expose.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironman.pharmasales.application.dto.category.CategoryDto;
import com.ironman.pharmasales.application.dto.category.CategorySaveDto;
import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.persistence.repository.CategoryRepository;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    private AutoCloseable closeable;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void canFindCategoryById() throws Exception {
        // given
        Long id = 2L;

        Category category = Category.builder()
                .id(id)
                .name("Categoria 1")
                .state(State.ACTIVE.getValue())
                .build();

        Mockito.when(categoryRepository.findById(id))
                .thenReturn(Optional.of(category));

        // when
        var uri = MockMvcRequestBuilders.get("/categories/{id}", id);
        ResultActions response = mockMvc.perform(uri);
        log.info("canFindCategoryById: " + response);

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(category.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(category.getName())));

    }

    @Test
    void canFindAllCategories() throws Exception {
        // given
        Category category1 = Category.builder()
                .id(1L)
                .name("Categoria 1")
                .state(State.ACTIVE.getValue())
                .build();

        Category category2 = Category.builder()
                .id(2L)
                .name("Categoria 2")
                .state(State.DISABLE.getValue())
                .build();

        Category category3 = Category.builder()
                .id(3L)
                .name("Categoria 3")
                .state(State.ACTIVE.getValue())
                .build();

        Mockito.when(categoryRepository.findAll())
                .thenReturn(List.of(category1, category2, category3));

        // when
        var uri = MockMvcRequestBuilders.get("/categories");
        ResultActions response = mockMvc.perform(uri);

        log.info("canFindAllCategories: " + response);


        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(category1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(category2.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].state.value", CoreMatchers.is(State.DISABLE.getValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", CoreMatchers.is(category3.getName())));
    }

    @Test
    void canCreateCategory() throws Exception {
        // given
        Category category = Category.builder()
                .id(1L)
                .name("Categoria")
                .description("Detalle")
                .state(State.ACTIVE.getValue())
                .build();

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category);

        // when
        CategorySaveDto categoryDto = CategorySaveDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();

        var uri = MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(categoryDto));

        ResultActions response = mockMvc.perform(uri);
        log.info("canCreateCategory: " + response);


        // then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(category.getName())));

    }

    @Test
    void canEditCategory() throws Exception {
        // given
        Long id = 1L;
        Category category = Category.builder()
                .id(id)
                .name("Categoria")
                .description("Detalle")
                .state(State.ACTIVE.getValue())
                .build();

        Mockito.when(categoryRepository.findById(id))
                        .thenReturn(Optional.of(category));

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category);

        // when
        CategorySaveDto categoryDto = CategorySaveDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();

        var uri = MockMvcRequestBuilders.put("/categories/{id}", category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(categoryDto));

        ResultActions response = mockMvc.perform(uri);
        log.info("canCreateCategory: " + response);


        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(category.getName())));

    }

    @Test
    void canDisableCategory() throws Exception {
        // given
        Long id = 1L;
        Category category = Category.builder()
                .id(id)
                .name("Categoria")
                .description("Detalle")
                .state(State.ACTIVE.getValue())
                .build();

        Mockito.when(categoryRepository.findById(id))
                .thenReturn(Optional.of(category));

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category);

        // when
        MockHttpServletRequestBuilder uri = MockMvcRequestBuilders.delete("/categories/{id}", category.getId());
        ResultActions response = mockMvc.perform(uri);
        log.info("canDisableCategory: " + response);

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.state.value", CoreMatchers.is(State.DISABLE.getValue())));
    }
}