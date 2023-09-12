package princessandknights.princesshoppang.webSocket.service;

import lombok.extern.slf4j.Slf4j;
import princessandknights.princesshoppang.game.model.GameRoom;
import princessandknights.princesshoppang.game.model.Player;
import princessandknights.princesshoppang.game.service.GameService;
import princessandknights.princesshoppang.game.service.MatchingService;
import princessandknights.princesshoppang.user.entity.User;
import princessandknights.princesshoppang.user.repository.UserRepository;
import princessandknights.princesshoppang.webSocket.RedisSubscriber;
import princessandknights.princesshoppang.webSocket.model.ChatMessage;
import princessandknights.princesshoppang.webSocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.awt.geom.GeneralPath;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final GameService gameService;
    private final UserRepository userRepository;
    private final MatchingService matchingService;

    /**
     * destination정보에서 roomId 추출
     */
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex+1);
        else
            return "";
    }


    /**
     * 채팅방에 메시지 발송
     */
//    public void sendChatMessage(ChatMessage chatMessage) {
//        log.info("sendChatMessage {}", chatMessage);
//
//
//        Optional<User> userOptional = userRepository.findById(chatMessage.getUserId());
//        User user = userOptional.get();
//        log.info("sender {}", chatMessage.getUserId());
//
//        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
//            log.info("ENTER로 들어옴");
//
//            chatMessage.setMessage(chatMessage.getSender() + "가 방에 입장했습니다.");
//            chatMessage.setSender("[알림]");
//
//        } else if (ChatMessage.MessageType.QUIT.equals(chatMessage.getType())) {
//            chatMessage.setMessage(chatMessage.getSender() + "가 방에서 나갔습니다.");
//            chatMessage.setSender("[알림]");
//
//        } else if (ChatMessage.MessageType.MATCH.equals(chatMessage.getType())) {
//            log.info("MATCH로 들어옴");
//            String mbti = gameService.intToMbti(user.getMbti());
//            GameRoom.TitleType titleType = matchingService.startMatching(user, mbti);
//
//            if (titleType.equals(GameRoom.TitleType.NOMATCH.getKrTitle())){
//                log.info("아직 플레이 가능 인원수가 모이지 않았습니다.");
//
//            } else {
//                log.info("플레이 가능 인원수가 모였습니다.");
//                GameRoom gameRoom = matchingService.match(titleType);
//                log.info("gameRoom: {}", gameRoom);
//                // data에 gameRoom 정보 넣기
//                chatMessage.setData(gameRoom);
//                chatMessage.setType(ChatMessage.MessageType.MATCHED);
//            }
//
//        } else if (ChatMessage.MessageType.MATCHED.equals(chatMessage.getType())) {
//
//        } else if (ChatMessage.MessageType.ACCEPTED.equals(chatMessage.getType())) {
//
//
//        } else if (ChatMessage.MessageType.START.equals(chatMessage.getType())) {
//
//        } else if (ChatMessage.MessageType.SUGGESTION.equals(chatMessage.getType())) {
//
//        } else if (ChatMessage.MessageType.VOTE.equals(chatMessage.getType())) {
//
//        } else if (ChatMessage.MessageType.VOTED.equals(chatMessage.getType())) {
//
//        } else if (ChatMessage.MessageType.RESULT.equals(chatMessage.getType())) {
//
//        } else if (ChatMessage.MessageType.END.equals(chatMessage.getType())) {
//
//        }
//
//        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
//        log.info("channelTopic {}", channelTopic.getTopic());
//    }

    public void sendChatMessage(ChatMessage chatMessage) {
//        chatMessage.setUserCount(chatRoomRepository.getUserCount(chatMessage.getRoomId()));
        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "가 방에 입장했습니다.");
            chatMessage.setSender("[알림]");
        } else if (ChatMessage.MessageType.QUIT.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "가 방에서 나갔습니다.");
            chatMessage.setSender("[알림]");
        } else {
            log.info("else로 들어오긴하냐?");
            chatMessage.setSender("공주");
            chatMessage.setMessage(chatMessage.getSender() + "가 메세지를 보냈습니다.");
        }
        log.info("channelTopic: {}",channelTopic.getTopic());
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }

}
