package vn.com.t3h.finish_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.t3h.finish_project.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity , Integer> {
    CategoryEntity findCategoryEntitiesById(Integer id);

}
