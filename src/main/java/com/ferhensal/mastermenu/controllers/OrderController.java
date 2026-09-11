package com.ferhensal.mastermenu.controllers;

import com.ferhensal.mastermenu.models.OrderEvent;
import com.ferhensal.mastermenu.services.interfaces.OrderService;
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
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/comanda")
    public ResponseEntity<?> verifyOrder(
            @Valid @RequestBody OrderEvent orderEvent
    ){
        log.info(
                "[API-COMANDA] Solicitud recibida. restaurante={}, mesa={}, tipo={}",
                orderEvent.restauranteId(),
                orderEvent.mesa(),
                orderEvent.type()
        );

        orderService.verifyOrder(orderEvent);

        log.info(
                "[API-COMANDA] Solicitud procesada. restaurante={}, mesa={}",
                orderEvent.restauranteId(),
                orderEvent.mesa()
        );

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Orden procesada correctamente"
                )
        );
    }

    @PostMapping("/kitchen")
    public ResponseEntity<?> sendOrderToKitchen(
            @Valid @RequestBody OrderEvent orderEvent
    ){
        log.info(
                "[API-COCINA] Solicitud recibida. restaurante={}, mesa={}, tipo={}",
                orderEvent.restauranteId(),
                orderEvent.mesa(),
                orderEvent.type()
        );

        orderService.sendOrderToKitchen(orderEvent);

        log.info(
                "[API-COCINA] Solicitud procesada. restaurante={}, mesa={}",
                orderEvent.restauranteId(),
                orderEvent.mesa()
        );

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Orden enviada a cocina correctamente"
                )
        );
    }

    @PostMapping("/complete")
    public ResponseEntity<?> markOrderAsReadyForPayment(
            @Valid @RequestBody OrderEvent orderEvent
    ) {
        log.info(
                "[API-SERVIDA] Solicitud recibida. restaurante={}, mesa={}, tipo={}",
                orderEvent.restauranteId(),
                orderEvent.mesa(),
                orderEvent.type()
        );

        orderService.setOrderReadyForPayment(orderEvent);

        log.info(
                "[API-SERVIDA] Solicitud procesada. restaurante={}, mesa={}",
                orderEvent.restauranteId(),
                orderEvent.mesa()
        );

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Orden servida correctamente y marcada como pendiente de pago."
                )
        );
    }

    @PostMapping("/payment-completed")
    public ResponseEntity<?> completeOrder(
            @Valid @RequestBody OrderEvent orderEvent
    ) {
        log.info(
                "[API-PAGO] Solicitud recibida. restaurante={}, mesa={}, tipo={}",
                orderEvent.restauranteId(),
                orderEvent.mesa(),
                orderEvent.type()
        );

        orderService.completeOrder(orderEvent);

        log.info(
                "[API-PAGO] Solicitud procesada. restaurante={}, mesa={}",
                orderEvent.restauranteId(),
                orderEvent.mesa()
        );

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Pago registrado correctamente y orden cerrada."
                )
        );
    }
}
