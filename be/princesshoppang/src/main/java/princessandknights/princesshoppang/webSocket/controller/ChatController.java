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

//    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

//    private final UserService userService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {;

        String nickname = message.getSender();

        // 로그인 회원 정보로 대화명 설정
//        message.setSender(nickname);

        log.info("[TEST] sender: "+message.getSender());
        log.info("[TEST] type: "+message.getType());
        log.info("[TEST] roomId: "+message.getRoomId());
        log.info("[TEST] message: "+message.getMessage());
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        log.info("[TEST] usercount" +  chatRoomRepository.getUserCount(message.getRoomId()));

        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);

        message.setImgUrl("");


    }

//        public void message(ChatMessage message, @Header("AUTH") String token) {
//            String email = jwtTokenProvider.getUserEmailFromJwt(token.toString());
//            Optional<User> userOp = userService.findByUserEmail(email);
//            if(userOp.isPresent()) {
//                User user = userOp.get();
//                String nickname = user.getUsername();
//                // 로그인 회원 정보로 대화명 설정
//                message.setSender(nickname);
//                log.info("[TEST] "+message.getSender());
//                // 채팅방 인원수 세팅
//                message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
//                String imgUrl = user.getProfile();
//                message.setImgUrl(imgUrl);
//                // Websocket에 발행된 메시지를 redis로 발행(publish)
//                chatService.sendChatMessage(message);
//            }
//        }
}

