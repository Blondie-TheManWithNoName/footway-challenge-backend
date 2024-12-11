package footway.challenge.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import footway.challenge.entities.DigitalProduct;
import footway.challenge.entities.PhysicalProduct;

@Repository
public interface DigitalProductRepo extends JpaRepository<DigitalProduct, String> {

    @Query("SELECT p FROM DigitalProduct p WHERE p.sku = :sku")
    Optional<DigitalProduct> findBySku(@Param("sku") String sku);

    @Query("SELECT p FROM DigitalProduct p WHERE p.EAN = :ean")
    DigitalProduct findByEan(@Param("ean") String ean);

    @Query("SELECT p FROM DigitalProduct p WHERE p.sku = :sku OR p.EAN = :ean")
    Optional<DigitalProduct> findBySkuOrEan(@Param("sku") String sku, @Param("ean") String ean);

    // Search by name & description
    @Query(value = "SELECT * FROM digital_product WHERE MATCH(name, description) AGAINST(:search IN BOOLEAN MODE)", nativeQuery = true)
    Page<DigitalProduct> findByNameAndDescription(@Param("search") String search, Pageable pageable);

}
