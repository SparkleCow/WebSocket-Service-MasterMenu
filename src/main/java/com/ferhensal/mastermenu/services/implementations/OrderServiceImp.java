package com.ferhensal.mastermenu.services.implementations;

import com.ferhensal.mastermenu.models.OrderEvent;
import com.ferhensal.mastermenu.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final WebSocketService webSocketService;

    @Override
    public void sendOrderToKitchen(OrderEvent orderEvent) {
        webSocketService.sendOrderToKitchen(orderEvent);
    }

    @Override
    public void verifyOrder(OrderEvent orderEvent) {
        webSocketService.verifyOrder(orderEvent);
    }

    @Override
    public void setOrderReadyForPayment(OrderEvent orderEvent) {
        webSocketService.notifyOrderReadyForPayment(orderEvent);
    }

    @Override
    public void completeOrder(OrderEvent orderEvent) {
        webSocketService.completeOrderPayment(orderEvent);
    }

}
