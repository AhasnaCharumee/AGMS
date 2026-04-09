package lk.ijse.sensortelemetryservice.client;

import lk.ijse.sensortelemetry.dto.AuthRequest;
import lk.ijse.sensortelemetry.dto.AuthResponse;
import lk.ijse.sensortelemetry.dto.Device;
import lk.ijse.sensortelemetry.dto.TelemetryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "external-iot-service", url = "http://104.211.95.241:8080/api")
public interface ExternalIoTClient {

    @PostMapping("/auth/login")
    AuthResponse login(@RequestBody AuthRequest request);

    @GetMapping("/devices")
    List<Device> getAllDevices(@RequestHeader("Authorization") String token);

    @GetMapping("/devices/telemetry/{deviceId}")
    TelemetryResponse getTelemetry(
            @RequestHeader("Authorization") String token,
            @PathVariable("deviceId") String deviceId
    );
}