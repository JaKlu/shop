package ovh.jakubk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.jakubk.shop.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByBranAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);
}
