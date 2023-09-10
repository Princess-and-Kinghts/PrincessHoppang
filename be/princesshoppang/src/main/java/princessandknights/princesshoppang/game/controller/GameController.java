package princessandknights.princesshoppang.game.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import princessandknights.princesshoppang.game.model.GameMessage;
import princessandknights.princesshoppang.game.repository.GameRoomRepository;
import princessandknights.princesshoppang.game.service.GameService;
import princessandknights.princesshoppang.game.service.MatchingService;
import princessandknights.princesshoppang.user.entity.User;
import princessandknights.princesshoppang.user.repository.UserRepository;
import princessandknights.princesshoppang.webSocket.model.ChatMessage;
import princessandknights.princesshoppang.webSocket.repository.ChatRoomRepository;
import princessandknights.princesshoppang.webSocket.service.ChatService;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@Controller
public class GameController {

//    private final JwtTokenProvider jwtTokenProvider;
    private final GameRoomRepository gameRoomRepository;
    private final GameService gameService;
    private final UserRepository userRepository;

//    private final UserService userService;
    private final MatchingService matchingService;

    /**
     * websocket "/pub/game"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/game")
    public void message(GameMessage message) {;
        log.info("GAME");
        String nickname = message.getNickname();

        // 로그인 회원 정보로 대화명 설정
//        message.setSender(nickname);

        log.info("[TEST] type: "+message.getType());
        log.info("[TEST] roomId: "+message.getRoomId());
        log.info("[TEST] message: "+message.getMessage());
        // 채팅방 인원수 세팅
//        message.setUserCount(gameRoomRepository.getUserCount(message.getRoomId()));
        log.info("[TEST] usercount" +  gameRoomRepository.getUserCount(message.getRoomId()));



        // Websocket에 발행된 메시지를 redis로 발행(publish)
        gameService.sendChatMessage(message);
    }

    /**
     * 게임 시작
     */
    @MessageMapping("/match")
    public void startMatching(GameMessage message) {

        Optional<User> userOptional = userRepository.findById(message.getUserId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String mbti = gameService.intToMbti(user.getMbti());

            matchingService.addToQueue(user, mbti);
        }
    }

}

