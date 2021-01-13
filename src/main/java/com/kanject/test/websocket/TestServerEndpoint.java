package com.kanject.test.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/**
 * ServerEndpoint极简版
 *
 * @author guangjie.liang
 * @date 2021/01/13 16:20:46
 */
@Slf4j
@Component
@ServerEndpoint("/ws/test")//把当前类标识成一个WebSocket的服务端
public class TestServerEndpoint {

    @OnOpen
    public void onOpen() {
        log.info("TestServerEndpoint is on open.");
    }

    @OnClose
    public void onClose() {
        log.info("TestServerEndpoint is on close.");
    }

    /**
     * OnError方法必须要有Throwable参数，否则抛：
     *  javax.websocket.DeploymentException: No Throwable parameter was present on the method [onError] of class [..TestServerEndpoint] that was annotated with OnError
     */
    @OnError
    public void onError(Throwable error) {
        log.info("TestServerEndpoint is on error =====> {}", error.getMessage());
    }

    /** @OnMessage 方法必须要有参数
     *  否则提示： @OnMessage method must have parameters */
    @OnMessage
    public void onMessage(String message) {
        log.info("TestServerEndpoint is on message =====> {}", message);
        if (message.equals("I am your Dad.")) {
            throw new RuntimeException("Dad, I am sorry.");
        }
    }

}
