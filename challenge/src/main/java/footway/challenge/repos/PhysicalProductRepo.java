package footway.challenge.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import footway.challenge.entities.PhysicalProduct;

@Repository
public interface PhysicalProductRepo extends JpaRepository<PhysicalProduct, String> {

        @Query("SELECT p FROM PhysicalProduct p WHERE p.sku = :sku")
        Optional<PhysicalProduct> findBySku(@Param("sku") String sku);

        @Query("SELECT p FROM PhysicalProduct p WHERE p.EAN = :ean")
        PhysicalProduct findByEan(@Param("ean") String ean);

        // Search by name & description
        @Query(value = "SELECT * FROM physical_product WHERE MATCH(name, description) AGAINST(:search IN BOOLEAN MODE)", nativeQuery = true)
        Page<PhysicalProduct> findByNameAndDescription(@Param("search") String search, Pageable pageable);

}
