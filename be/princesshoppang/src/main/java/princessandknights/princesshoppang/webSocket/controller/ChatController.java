package princessandknights.princesshoppang.webSocket.controller;

import java.util.Optional;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

//import kr.co.rwm.entity.User;
import princessandknights.princesshoppang.webSocket.model.ChatMessage;
import princessandknights.princesshoppang.webSocket.repository.ChatRoomRepository;
import princessandknights.princesshoppang.webSocket.service.ChatService;
//import princessandknights.princesshoppang.webSocket.service.JwtTokenProvider;
//import princessandknights.princesshoppang.webSocket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@Controller
public class ChatController {
    private final ChatService chatService;


    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {;
        log.info("message 들어왔다.");
        log.info("{}를 publish합니다.", message);
        chatService.sendChatMessage(message);

    }

}

