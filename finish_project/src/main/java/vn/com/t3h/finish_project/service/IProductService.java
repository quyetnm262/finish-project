package vn.com.t3h.finish_project.service;

import org.springframework.data.domain.Pageable;
import vn.com.t3h.finish_project.model.dto.ProductDto;

import java.util.List;

public interface IProductService {

    List<ProductDto> getProducts();

    List<ProductDto> getProductsByCategoryId(Integer categoryId);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, Integer id);

    void deleteProduct(Integer id);



}
