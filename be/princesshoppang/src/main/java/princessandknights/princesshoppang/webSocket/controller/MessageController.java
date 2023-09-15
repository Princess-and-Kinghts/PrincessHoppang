package princessandknights.princesshoppang.webSocket.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import princessandknights.princesshoppang.game.model.GameRoom;
import princessandknights.princesshoppang.game.repository.GameRoomRepository;
import princessandknights.princesshoppang.game.service.GameService;
import princessandknights.princesshoppang.game.service.MatchingService;
import princessandknights.princesshoppang.user.entity.User;
import princessandknights.princesshoppang.user.repository.UserRepository;
import princessandknights.princesshoppang.webSocket.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final UserRepository userRepository;
    private final GameRoomRepository gameRoomRepository;
    private final SimpMessageSendingOperations messageSendingOperations;
    private final GameService gameService;
    private final MatchingService matchingService;


    /**
        인게임 채팅 관리
        /pub/game              메시지 발행
        /topic/channelId        구독
     */

    @MessageMapping("/game")
    public void playGame(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {

        log.info("{}로 보내는 게임 채팅", message.getChannelId());
        log.info("게임으로 온 message: {}", message);
        headerAccessor.getSessionAttributes().put("username", message.getUserId());

        Optional<User> userOptional = userRepository.findById(message.getUserId());
        try {
            User user = userOptional.get();
            log.info("user: {}", user.getNickname());

            /**
             * MATCH 시작 요청
             **/

            if (Message.MessageType.MATCH.equals(message.getType())) {
                log.info("MATCH~~~!!!");

                String mbti = gameService.intToMbti(user.getMbti());
                GameRoom.TitleType titleType = matchingService.startMatching(user, mbti);

                if (GameRoom.TitleType.NOMATCH.equals(titleType)) {
                    log.info("아직 플레이 가능 인원수가 모이지 않았습니다.");

                } else {
                    log.info("플레이 가능 인원수가 모였습니다.");
                    GameRoom gameRoom = matchingService.match(titleType);
                    log.info("channelId @ message: {}", message.getChannelId());
                    // data에 gameRoom 정보 넣기
                    message.setData(gameRoom);
                    message.setChannelId("matching");
                    // type MATCHED로 설정
                    message.setType(Message.MessageType.MATCHED);
                }
            /**
             * 전원이 게임 수락시 게임 시작 알림
             * */
            } else if (Message.MessageType.ACCEPT.equals(message.getType())) {
                log.info("ACCEPT~~~!!!");
                String channelId = message.getChannelId();
                int playerCount = gameRoomRepository.addPlayerCount(channelId);
                if (playerCount == 7) {
                    // 게임 시작 시간 기록
                    gameRoomRepository.updateStartTime(channelId);
                    log.info("addPlayerCount에서 7명 모두 수락해서 {}방이 열린다.", channelId);
                    message.setType(Message.MessageType.START);
                    message.setChannelId("matching");
                    message.setUserId(0L);
                    message.setData(gameRoomRepository.getRoomInfo(channelId));
                }

            /**
             * 각 유저가 게임 입장 알림
             * */
            } else if (Message.MessageType.ENTER.equals(message.getType())) {
                log.info("{}번 방에 들어온 데이터: {}", message.getChannelId(), message.getData());

            /**
             * 게임 시간 카운팅 시작
             * */
            } else if (Message.MessageType.START.equals(message.getType())) {

            } else if (Message.MessageType.SUGGESTION.equals(message.getType())) {

            } else if (Message.MessageType.VOTE.equals(message.getType())) {

            } else if (Message.MessageType.VOTED.equals(message.getType())) {

            } else if (Message.MessageType.QUIT.equals(message.getType())) {

            } else if (Message.MessageType.END.equals(message.getType())) {

            }  else if (Message.MessageType.RESULT.equals(message.getType())) {

            }

        } catch (EmptyResultDataAccessException e)  {
            throw new RuntimeException("userId가 존재하지 않습니다.");
        }

        messageSendingOperations.convertAndSend("/topic/" + message.getChannelId(), message);
    }

    /**
         DM 채팅 관리
         /pub/chat              메시지 발행
         /topic/channelId        구독
     */
    @MessageMapping("/chat")
    public void sendDm(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        log.info("{}로 보내는 DM", message.getChannelId());

        headerAccessor.getSessionAttributes().put("username", message.getUserId());
        messageSendingOperations.convertAndSend("/topic/" + message.getChannelId(), message);

        // db에 저장하는 로직 필요

    }

    private boolean binToMbti(Character bin) {
        if (bin == '0') {
            return true;
        }
        return false;
    }
}
