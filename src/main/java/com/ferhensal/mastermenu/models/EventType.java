package com.ferhensal.mastermenu.models;

public enum EventType {

    ORDER_VALIDATE,     // Se debe validar la solicitud
    ORDER_KITCHEN,      // La solicitud fue aceptada y pasó a cocina
    ORDER_CONFIRMED,    // El pedido fue entregado al cliente
    ORDER_CLOSED,       // La mesa quedó completamente cerrada/pagada
    ORDER_CANCELLED,    // El cliente a cancelado o eliminado toda solicitud

    WAITER_CALLED,

    BILL_REQUESTED
}