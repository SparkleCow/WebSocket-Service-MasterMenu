package com.ferhensal.mastermenu.services.implementations;

import com.ferhensal.mastermenu.models.WaiterEvent;
import com.ferhensal.mastermenu.services.interfaces.WaiterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WaiterServiceImp implements WaiterService {

    private final WebSocketService webSocketService;

    @Override
    public void callWaiter(WaiterEvent waiterEvent) {
        webSocketService.sendWaiterCall(waiterEvent);
    }
}
