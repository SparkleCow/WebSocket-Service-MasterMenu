package com.ferhensal.mastermenu.controllers;

import com.ferhensal.mastermenu.models.WaiterEvent;
import com.ferhensal.mastermenu.services.interfaces.WaiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/waiter")
@Slf4j
public class WaiterController {

    private final WaiterService waiterService;

    @PostMapping("/call")
    public ResponseEntity<?> callWaiter(
            @Valid @RequestBody WaiterEvent waiterEvent
    ) {

        log.info(
                "[API-MESERO] Solicitud recibida. restaurante={}, mesa={}, tipo={}",
                waiterEvent.restauranteId(),
                waiterEvent.mesa(),
                waiterEvent.type()
        );

        waiterService.callWaiter(waiterEvent);

        log.info(
                "[API-MESERO] Solicitud procesada. restaurante={}, mesa={}",
                waiterEvent.restauranteId(),
                waiterEvent.mesa()
        );

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Llamada al mesero procesada correctamente"
                )
        );
    }
}
