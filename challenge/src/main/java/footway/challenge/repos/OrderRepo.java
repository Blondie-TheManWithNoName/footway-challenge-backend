package footway.challenge.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import footway.challenge.digitalProduct.dtos.DigitalProductProjection;
import footway.challenge.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(value = """
            SELECT d.sku AS sku, d.name AS name, d.EAN AS EAN
            FROM digital_product d
            LEFT JOIN order_digital_products odp
            ON d.sku = odp.digital_product_sku
            WHERE odp.order_id = :orderId
              AND MATCH(d.name, d.description) AGAINST(:search IN BOOLEAN MODE)
            """, nativeQuery = true)
    Page<DigitalProductProjection> findByOrderIdAndSearch(
            @Param("orderId") int orderId,
            @Param("search") String search,
            Pageable pageable);

    @Query(value = """
            SELECT d.sku AS sku, d.name AS name, d.EAN AS EAN
            FROM digital_product d
            LEFT JOIN order_digital_products odp
            ON d.sku = odp.digital_product_sku
            WHERE odp.order_id = :orderId
            """, nativeQuery = true)
    Page<DigitalProductProjection> findByOrderId(
            @Param("orderId") int orderId,
            Pageable pageable);
}