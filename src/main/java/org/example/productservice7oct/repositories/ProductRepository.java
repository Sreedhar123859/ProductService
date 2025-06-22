package org.example.productservice7oct.repositories;

import org.example.productservice7oct.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Product save(Product product);

    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    List<Product> findAllByCategory_Subcategory_SurnameEquals(String subcategorySurname);

    @Query("select p from Product p" +
            " where p.category.subcategory.surname = :subCategoryName")
    List<Product> something1(@Param("subCategoryName") String subCategoryName);

    @Query("select p from Product p where p.id> :idGreaterThan")
    List<Product> something(@Param("idGreaterThan") Long idGreaterThan);

    @Query(value = CustomQueries.GET_PRODUCT_WITH_SUB_CATEGORY_NAME, nativeQuery = true)
    List<Product> something2();

    List<Product> findByTitleContaining(String title);


}
