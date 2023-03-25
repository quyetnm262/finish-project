package vn.com.t3h.finish_project.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.t3h.finish_project.entity.ProductEntity;
import vn.com.t3h.finish_project.model.dto.ProductDto;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity findByName(String productName);
//    Page<ProductEntity> pageProduct(Pageable pageable);

}
