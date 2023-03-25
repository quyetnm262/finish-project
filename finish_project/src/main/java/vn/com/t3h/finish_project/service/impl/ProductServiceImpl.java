package vn.com.t3h.finish_project.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.t3h.finish_project.entity.CategoryEntity;
import vn.com.t3h.finish_project.entity.ProductEntity;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.repository.CategoryRepository;
import vn.com.t3h.finish_project.repository.ProductRepository;
import vn.com.t3h.finish_project.service.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {


    /*---------Inject start----------------*/
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    /*------------Inject end-------------*/

    @Override
    public List<ProductDto> getProducts() {

        List<ProductEntity> productEntityList = productRepository.findAll();

        List<ProductDto> productDtoList = productEntityList.stream().map(this::productEntityToDto)
                .collect(Collectors.toList());
        return productDtoList;
    }

//    @Override
//    public Page<ProductEntity> pageProducts(int index) {
//
//        Pageable pageable = PageRequest.of(index,5);
//        Page<ProductEntity> productEntityPage = productRepository.pageProduct(pageable);
//
//        return productEntityPage;
//    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Integer categoryId) {
        List<ProductDto> list = this.getProducts();
        List<ProductDto> productDtoList = list.stream().filter(productDto -> productDto.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
        return productDtoList;
    }

    @Override
    public ProductDto getProductByName(String name) {
        ProductEntity entity = productRepository.findByName(name);

        return productEntityToDto(entity);
    }

    @Override
    public ProductDto getProductById(Integer id) {

        ProductEntity entity = productRepository.findById(id).get();
        return productEntityToDto(entity);
    }

    @Override
    public ProductDto createProduct(ProductDto dto) {
        ProductEntity productEntity = productDtoToEntity(dto);
        productEntity = productRepository.save(productEntity);
        ProductDto productDto = productEntityToDto(productEntity);
        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer id) {

        ProductEntity productEntity = productDtoToEntity(productDto);
        productEntity.setId(id);
        productEntity = productRepository.save(productEntity);
        ProductDto dto = productEntityToDto(productEntity);
        return dto;
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }




    /*----------Converter start-------------------*/

    private ProductEntity productDtoToEntity(ProductDto dto){
        ProductEntity entity = modelMapper.map(dto,ProductEntity.class);
        CategoryEntity categoryEntity = new CategoryEntity();
        if (dto.getCategoryId() != null){
            categoryEntity = categoryRepository.findCategoryEntitiesById(dto.getCategoryId());
            entity.setCategory(categoryEntity);
        }
        return entity;

    }

    private ProductDto productEntityToDto(ProductEntity productEntity){
        ProductDto dto = modelMapper.map(productEntity,ProductDto.class);
        dto.setCategoryId(productEntity.getCategoryId());
        return dto;
    }

    /*----------Converter end-------------------*/


}
