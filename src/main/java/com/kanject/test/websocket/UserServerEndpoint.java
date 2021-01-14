package com.kanject.test.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

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

    /** 用户在线数 */
    private static int onlineCount;

    /** 用户-websocket服务端 Map */
    private static Map<String, UserServerEndpoint> userMap = new ConcurrentHashMap<>();

//    /** 用户名set，艾特用户的时候用到 */
//    private static List<String> atUsernameList = new CopyOnWriteArrayList<>();

    /** 记录用户会话，实例变量，每个用户一个 */
    private Session session;

    /** 记录用户名，实例变量，每个用户一个 */
    private String username;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        this.username = username;
        userMap.put("@" + this.username, this);
//        atUsernameList.add("@" + this.username);
        onlineCountIncrease();
        broadcast(String.format("[%s]进入了聊天室, 当前聊天室人数为[%d]", this.username, getOnlineCount()));
        log.info(String.format("[%s]进入了聊天室, 当前聊天室人数为[%d]", this.username, getOnlineCount()));
        log.info("userMap =====> {}", userMap);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        userMap.remove("@" + this.username);
//        atUsernameList.remove("@" + this.username);
        onlineCountDecrease();
        broadcast(String.format("[%s]退出了聊天室, 当前聊天室人数为[%d]", this.username, getOnlineCount()));
        log.info(String.format("[%s]退出了聊天室, 当前聊天室人数为[%d]", this.username, getOnlineCount()));
        log.info("userMap =====> {}", userMap);
    }

    /**
     * 发生错误时调用
     * OnError方法必须要有Throwable参数，否则抛：
     *  javax.websocket.DeploymentException: No Throwable parameter was present on the method [onError] of class [..TestServerEndpoint] that was annotated with OnError
     */
    @OnError
    public void onError(Throwable error) {
        log.error("TestServerEndpoint is on error =====> {}", error.getMessage());
    }

    /** 收到客户端消息后调用的方法
     *  @OnMessage 方法必须要有参数
     *  否则提示： @OnMessage method must have parameters */
    @OnMessage
    public void onMessage(String message) {
        log.info("TestServerEndpoint is on message =====> {}", message);
        if (message.equals("I am your Dad.")) {
            throw new RuntimeException("Dad, I am sorry.");
        }

        if (!message.contains("@")) {//消息文本中不含'@'，直接广播消息
            broadcast(message);
        } else {//消息文本中包含'@'，执行以下逻辑
            boolean broadcastFlag = true;
            for (String atUsername : userMap.keySet()) {
                if (message.contains(atUsername)) {
                    broadcastFlag = false;
                    log.info("@ user =====> [{}]", atUsername.substring(1));
//                    userMap.get(atUsername.substring(1)).sendMessage(message);
                    userMap.get(atUsername).sendMessage(message);
                }
            }
            if (broadcastFlag) {
                broadcast(message);
            }
        }
    }

    /**
     * 广播消息
     */
    private void broadcast(String message) {
        for (String key : userMap.keySet()) {
            userMap.get(key).sendMessage(message);
        }
    }

    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void onlineCountIncrease() {
        onlineCount++;
    }

    private static synchronized void onlineCountDecrease() {
        onlineCount--;
    }


}
