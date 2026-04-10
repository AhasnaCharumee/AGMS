package lk.ijse.automationcontrolservice.service;

import lk.ijse.automationcontrolservice.client.ZoneClient;
import lk.ijse.automationcontrolservice.dto.SensorData;
import lk.ijse.automationcontrolservice.dto.ZoneDTO;
import lk.ijse.automationcontrolservice.model.ActionLog;
import lk.ijse.automationcontrolservice.repository.ActionLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutomationService {

    private final ZoneClient zoneClient;
    private final ActionLogRepository actionLogRepository;

    public AutomationService(ZoneClient zoneClient, ActionLogRepository actionLogRepository) {
        this.zoneClient = zoneClient;
        this.actionLogRepository = actionLogRepository;
    }

    @Transactional
    public void processSensorData(SensorData sensorData) {
        try {
            ZoneDTO zone = zoneClient.getZoneById(sensorData.getZoneId());

            String action = "NORMAL";
            if (sensorData.getTemperature() > zone.getMaxTemp()) {
                action = "TURN_FAN_ON";
            } else if (sensorData.getTemperature() < zone.getMinTemp()) {
                action = "TURN_HEATER_ON";
            }

            ActionLog log = new ActionLog(
                    sensorData.getZoneId(),
                    sensorData.getDeviceId(),
                    sensorData.getTemperature(),
                    action
            );
            actionLogRepository.save(log);

            System.out.println("Automation triggered for Zone " + sensorData.getZoneId() +
                    " | Temp: " + sensorData.getTemperature() + "°C" +
                    " | Action: " + action);

        } catch (Exception e) {
            System.err.println("Failed to process automation for zone " + sensorData.getZoneId() + ": " + e.getMessage());
        }
    }

    public List<ActionLog> getAllLogs() {
        return actionLogRepository.findAll();
    }
}