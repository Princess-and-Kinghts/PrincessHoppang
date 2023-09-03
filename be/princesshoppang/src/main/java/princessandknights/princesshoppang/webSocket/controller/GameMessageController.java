package princessandknights.princesshoppang.webSocket.controller;

import princessandknights.princesshoppang.webSocket.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameMessageController {

    private final SimpMessageSendingOperations messageSendingOperations;

    /*
        /pub/game              메시지 발행
        /topic/channelId        구독
     */

    @MessageMapping("/game")
    public void startGame(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        log.info("newUser에서 찍히는 로그 {}", message.getChannelId());
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        messageSendingOperations.convertAndSend("/topic/" + message.getChannelId(), message);
    }
}
