package com.ferhensal.mastermenu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketsConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        /*
         * Habilita un broker de mensajes en memoria
         * Todos los clientes podrán suscribirse a destinos como:
         *   /topic/orders
         *   /topic/notifications
         *   /queue/...
         * Cada vez que el servidor publique un mensaje en uno de estos
         * destinos, todos los clientes suscritos lo recibirán automáticamente.
         */
        config.enableSimpleBroker("/topic", "/queue");
        /*
         * Prefijo utilizado para los mensajes enviados DESDE el cliente
         * HACIA el servidor.
         * Ejemplo:
         * Cliente:
         * stomp.send("/app/orders", ...);
         * Servidor:
         * @MessageMapping("/orders")
         */
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        /*
         * Endpoint principal para establecer la conexión WebSocket.
         * Los clientes deberán conectarse a:
         * http://localhost:8080/ws en local o a a la respectiva direccion tras producción
         */
        registry.addEndpoint("/ws")

                /*
                 * Permite conexiones desde cualquier origen.
                 * En producción es recomendable reemplazar "*"
                 * por los dominios autorizados.
                 */
                .setAllowedOriginPatterns("*")
                /*
                 * Habilita SockJS como mecanismo de respaldo.
                 * Si el navegador o la red no permiten WebSocket
                 * nativo, SockJS utilizará automáticamente otros
                 * mecanismos (long polling, streaming, etc.)
                 * sin que el cliente tenga que modificar su código.
                 */
                .withSockJS();
    }
}