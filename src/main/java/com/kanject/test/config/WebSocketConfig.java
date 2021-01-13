package com.kanject.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类
 *
 * @author guangjie.liang
 * @date 2021/01/13 16:00:59
 */
@Component
public class WebSocketConfig {

    /**
     * ServerEndpointExporter 作用
     *  自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @author: guangjie.liang
     * @date: 2021/1/13 下午4:06
     */
    @Bean
    public ServerEndpointExporter getServerEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
