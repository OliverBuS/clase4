package pucp.edu.pe.clase4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pucp.edu.pe.clase4.entity.Locations;

@Repository
public interface LocationsRepository extends JpaRepository<Locations,Integer> {
}
