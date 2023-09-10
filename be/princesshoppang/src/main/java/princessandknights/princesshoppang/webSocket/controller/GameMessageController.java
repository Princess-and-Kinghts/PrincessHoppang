package princessandknights.princesshoppang.webSocket.controller;

import org.springframework.web.socket.WebSocketSession;
import princessandknights.princesshoppang.user.entity.User;
import princessandknights.princesshoppang.user.repository.UserRepository;
import princessandknights.princesshoppang.webSocket.dto.MatchingQueueDto;
import princessandknights.princesshoppang.webSocket.dto.PlayerDto;
import princessandknights.princesshoppang.webSocket.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameMessageController {

    private final UserRepository userRepository;
    private final SimpMessageSendingOperations messageSendingOperations;


    /*
        /pub/game              메시지 발행
        /topic/channelId        구독
     */

    @MessageMapping("/game")
    public void startGame(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {

        log.info("newUser에서 찍히는 로그 {}", message.getChannelId());

        headerAccessor.getSessionAttributes().put("username", message.getSender());

        String sessionid = headerAccessor.getSessionId();

        if (message.getType() == "match") {
            Optional<User> userOptional = userRepository.findById(message.getSender());

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                PlayerDto player = PlayerDto.makePlayerDto(user);

                String mbti = Integer.toBinaryString(user.getMbti());

                // mbti별 deque에 넣어줌
                if (binToMbti(mbti.charAt(0))) {
                    E.add(player);
                } else {
                    I.add(player);
                }




            }

        }

        messageSendingOperations.convertAndSend("/topic/" + message.getChannelId(), message);
    }

    private boolean binToMbti(Character bin) {
        if (bin == '0') {
            return true;
        }
        return false;
    }
}
