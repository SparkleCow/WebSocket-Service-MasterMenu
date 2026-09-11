package com.ferhensal.mastermenu.services.interfaces;

import com.ferhensal.mastermenu.models.OrderEvent;

public interface OrderService {

    void sendOrderToKitchen(OrderEvent orderEvent);

    void verifyOrder(OrderEvent orderEvent);

    void setOrderReadyForPayment(OrderEvent orderEvent);

    void completeOrder(OrderEvent orderEvent);
}
