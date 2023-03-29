package vn.com.t3h.finish_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.t3h.finish_project.entity.ShoppingCartEntity;
import vn.com.t3h.finish_project.entity.UserEntity;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Integer> {
    ShoppingCartEntity findByUser(UserEntity user);
}
