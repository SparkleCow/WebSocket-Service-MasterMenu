package com.ferhensal.mastermenu.models;

public record OrderEvent(
        EventType type,
        Long restauranteId,
        String mesa
) {
}