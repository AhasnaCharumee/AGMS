package lk.ijse.automationcontrolservice.repository;

import lk.ijse.automationcontrolservice.model.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, String> {
}