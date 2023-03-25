package vn.com.t3h.finish_project.service;

import org.springframework.data.domain.Page;
import vn.com.t3h.finish_project.entity.ProductEntity;
import vn.com.t3h.finish_project.model.dto.ProductDto;

import java.util.List;

public interface IProductService {

    List<ProductDto> getProducts();

//    Page<ProductEntity> pageProducts(int index);

    List<ProductDto> getProductsByCategoryId(Integer categoryId);

    ProductDto getProductByName(String name);

    ProductDto getProductById(Integer id);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, Integer id);

    void deleteProduct(Integer id);



}
