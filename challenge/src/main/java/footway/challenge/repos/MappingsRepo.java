package footway.challenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import footway.challenge.entities.Mappings;

@Repository
public interface MappingsRepo extends JpaRepository<Mappings, Integer> {

    @Query("SELECT m FROM Mappings m WHERE m.physicalProduct.sku = :physicalSku AND m.digitalProduct.sku = :digitalSku AND m.order.id = :orderId")
    List<Mappings> findByProducts(@Param("physicalSku") String physicalSku, @Param("digitalSku") String digitalSku,
            @Param("orderId") int orderId);

    @Query("SELECT m FROM Mappings m WHERE m.physicalProduct.sku = :physicalSku AND m.order.id = :orderId")
    List<Mappings> findByPhysicalProduct(@Param("physicalSku") String physicalSku, @Param("orderId") int orderId);

    @Query("SELECT m FROM Mappings m WHERE m.digitalProduct.sku = :digitalSku AND m.order.id = :orderId")
    List<Mappings> findByDigitalProduct(@Param("digitalSku") String digitalSku, @Param("orderId") int orderId);

    @Query("SELECT m FROM Mappings m WHERE m.order.id = :orderId")
    List<Mappings> findByOrderId(@Param("orderId") int orderId);

}
