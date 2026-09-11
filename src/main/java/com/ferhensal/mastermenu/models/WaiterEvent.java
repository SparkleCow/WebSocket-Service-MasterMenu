package com.ferhensal.mastermenu.models;

public record WaiterEvent(
        EventType type,
        Long restauranteId,
        String mesa
) {
}
