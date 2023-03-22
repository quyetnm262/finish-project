package vn.com.t3h.finish_project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.t3h.finish_project.entity.CategoryEntity;
import vn.com.t3h.finish_project.entity.ProductEntity;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.repository.CategoryRepository;
import vn.com.t3h.finish_project.repository.ProductRepository;
import vn.com.t3h.finish_project.service.ICategoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {


    /*----------Inject start-------------------*/
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    /*----------Inject end-------------------*/


    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> dtoList = categoryRepository.findAll().stream().map(this::categoryEntityToDto)
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {

        CategoryEntity  categoryEntity = categoryRepository.findCategoryEntitiesById(id);
        CategoryDto categoryDto = categoryEntityToDto(categoryEntity);

        return categoryDto;
    }

    @Override
    public CategoryDto createCategory(CategoryDto dto) {

        CategoryEntity categoryEntity = categoryDtoToEntity(dto);
        categoryEntity = categoryRepository.save(categoryEntity);
        CategoryDto categoryDto = categoryEntityToDto(categoryEntity);
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto dto, Integer id) {

        CategoryEntity newCategory = categoryDtoToEntity(dto);
        newCategory.setId(id);

        CategoryEntity categoryEntity = categoryRepository.save(newCategory);
        CategoryDto categoryDto = categoryEntityToDto(categoryEntity);
        return categoryDto;
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    /*----------Converter start-------------------*/

    private CategoryEntity categoryDtoToEntity(CategoryDto categoryDto){
        CategoryEntity categoryEntity = modelMapper.map(categoryDto,CategoryEntity.class);
        List<ProductEntity> products = new ArrayList<>();
        if (categoryDto.getProductName() != null){
            List<String> productNames = Arrays.asList(categoryDto.getProductName());
            productNames.forEach(productName ->{
                ProductEntity productEntity = productRepository.findByName(productName);
                if (productEntity != null){
                    products.add(productEntity);
                }
            });
            categoryEntity.setProducts(products);
        }
        return categoryEntity;

    }

    private CategoryDto categoryEntityToDto(CategoryEntity entity) {
        CategoryDto categoryDto = modelMapper.map(entity, CategoryDto.class);
        if (entity.getProducts() != null){
            String [] productName = entity.getProducts().stream().map(ProductEntity::getName).toArray(String[]::new);
            categoryDto.setProductName(productName);
        }
        return categoryDto;
    }

    /*----------Converter end-------------------*/
}
