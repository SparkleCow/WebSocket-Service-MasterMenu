package com.ferhensal.mastermenu.services.implementations;

import com.ferhensal.mastermenu.models.OrderEvent;
import com.ferhensal.mastermenu.models.WaiterEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketService {

    private static final String ORDERS_TOPIC = "/topic/orders";
    private static final String KITCHEN_TOPIC = ORDERS_TOPIC + "/kitchen/";
    private static final String COMANDA_TOPIC = ORDERS_TOPIC + "/comanda/";
    private static final String SERVED_TOPIC = ORDERS_TOPIC + "/served/";
    private static final String PAYMENT_TOPIC = ORDERS_TOPIC + "/paid/";

    private static final String WAITER_TOPIC = "/topic/service/waiter/";

    private final SimpMessagingTemplate messagingTemplate;

    public void verifyOrder(OrderEvent orderEvent) {

        if (orderEvent == null) {
            throw new IllegalArgumentException("La orden no puede ser nula");
        }

        String destino = COMANDA_TOPIC + orderEvent.restauranteId();

        try {
            messagingTemplate.convertAndSend(destino, orderEvent);

            log.info(
                    "[COMANDA] Orden enviada. restaurante={}, mesa={}, tipo={}, destino={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino
            );

        } catch (Exception e) {
            log.error(
                    "[COMANDA] Error enviando orden. restaurante={}, mesa={}, tipo={}, destino={}, error={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino,
                    e.getMessage(),
                    e
            );
        }
    }

    public void sendOrderToKitchen(OrderEvent orderEvent) {
        if (orderEvent == null) {
            throw new IllegalArgumentException("La orden no puede ser nula");
        }

        String destino = KITCHEN_TOPIC + orderEvent.restauranteId();

        try {
            messagingTemplate.convertAndSend(destino, orderEvent);

            log.info(
                    "[COCINA] Orden enviada. restaurante={}, mesa={}, tipo={}, destino={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino
            );

        } catch (Exception e) {
            log.error(
                    "[COCINA] Error enviando orden. restaurante={}, mesa={}, tipo={}, destino={}, error={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino,
                    e.getMessage(),
                    e
            );
        }
    }


    public void sendWaiterCall(WaiterEvent waiterEvent) {

        if (waiterEvent == null) {
            throw new IllegalArgumentException("El evento del mesero no puede ser nulo.");
        }

        String destino = WAITER_TOPIC + waiterEvent.restauranteId();

        try {

            log.info(
                    "[MESERO] Enviando llamada. restaurante={}, mesa={}, tipo={}, destino={}",
                    waiterEvent.restauranteId(),
                    waiterEvent.mesa(),
                    waiterEvent.type(),
                    destino
            );

            messagingTemplate.convertAndSend(destino, waiterEvent);

            log.info(
                    "[MESERO] Llamada enviada correctamente. restaurante={}, mesa={}",
                    waiterEvent.restauranteId(),
                    waiterEvent.mesa()
            );

        } catch (Exception e) {

            log.error(
                    "[MESERO] Error enviando llamada. restaurante={}, mesa={}, tipo={}, destino={}, error={}",
                    waiterEvent.restauranteId(),
                    waiterEvent.mesa(),
                    waiterEvent.type(),
                    destino,
                    e.getMessage(),
                    e
            );

            throw new RuntimeException("No fue posible enviar el evento WebSocket.", e);
        }
    }

    public void notifyOrderReadyForPayment(OrderEvent orderEvent) {

        if (orderEvent == null) {
            throw new IllegalArgumentException("La orden no puede ser nula");
        }

        String destino = SERVED_TOPIC + orderEvent.restauranteId();

        try {

            messagingTemplate.convertAndSend(destino, orderEvent);

            log.info(
                    "[SERVIDA] Orden servida notificada. restaurante={}, mesa={}, tipo={}, destino={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino
            );

        } catch (Exception e) {

            log.error(
                    "[SERVIDA] Error notificando orden servida. restaurante={}, mesa={}, tipo={}, destino={}, error={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino,
                    e.getMessage(),
                    e
            );

            throw new RuntimeException("No fue posible enviar el evento WebSocket.", e);
        }
    }

    public void completeOrderPayment(OrderEvent orderEvent) {
        if (orderEvent == null) {
            throw new IllegalArgumentException("La orden no puede ser nula");
        }

        String destino = PAYMENT_TOPIC + orderEvent.restauranteId();

        try {

            messagingTemplate.convertAndSend(destino, orderEvent);

            log.info(
                    "[PAGO] Pago registrado notificado. restaurante={}, mesa={}, tipo={}, destino={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino
            );

        } catch (Exception e) {

            log.error(
                    "[PAGO] Error notificando pago. restaurante={}, mesa={}, tipo={}, destino={}, error={}",
                    orderEvent.restauranteId(),
                    orderEvent.mesa(),
                    orderEvent.type(),
                    destino,
                    e.getMessage(),
                    e
            );

            throw new RuntimeException("No fue posible enviar el evento WebSocket.", e);
        }
    }
}
