package footway.challenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import footway.challenge.entities.DigitalProduct;

@Repository
public interface DigitalProductRepo extends JpaRepository<DigitalProduct, String> {

}
