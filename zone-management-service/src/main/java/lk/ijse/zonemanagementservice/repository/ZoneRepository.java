package lk.ijse.zonemanagementservice.repository;

import lk.ijse.zonemanagementservice.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, String> {
}