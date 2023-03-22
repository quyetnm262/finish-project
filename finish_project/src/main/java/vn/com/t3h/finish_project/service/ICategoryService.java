package vn.com.t3h.finish_project.service;

import vn.com.t3h.finish_project.model.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(Integer id);

    CategoryDto createCategory(CategoryDto dto);

    CategoryDto updateCategory(CategoryDto dto, Integer id);

    void deleteCategoryById(Integer id);


}
