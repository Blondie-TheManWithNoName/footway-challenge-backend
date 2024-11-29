package footway.challenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import footway.challenge.entities.PhysicalProduct;

@Repository
public interface PhysicalProductRepo extends JpaRepository<PhysicalProduct, String> {

}
