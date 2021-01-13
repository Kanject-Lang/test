package com.kanject.test.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2021/01/13 16:14:12
 */
@Slf4j
@Component
@ServerEndpoint("/ws/user/{username}")//把当前类标识成一个WebSocket的服务端
public class UserServerEndpoint {

    /** 保存用户会话，实例变量，每个用户一个 */
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        try {
            session.getBasicRemote().sendText(String.format("[%s]进入了聊天室", username));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("[%s]进入了聊天室", username));
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        this.session = session;
        try {
            session.getBasicRemote().sendText(String.format("[%s]退出了聊天室", username));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("[%s]退出了聊天室", username));
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
