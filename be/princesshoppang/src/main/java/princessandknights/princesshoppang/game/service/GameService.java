package princessandknights.princesshoppang.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.game.model.GameMessage;
import princessandknights.princesshoppang.game.repository.GameRoomRepository;
import princessandknights.princesshoppang.user.entity.User;
import princessandknights.princesshoppang.user.repository.UserRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final GameRoomRepository gameRoomRepository;

    private final UserRepository userRepository;
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
    public void sendChatMessage(GameMessage gameMessage) {

        Optional<User> userOptional = userRepository.findById(gameMessage.getUserId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (GameMessage.MessageType.ENTER.equals(gameMessage.getType())) {
                gameMessage.setMessage(gameMessage.getNickname() + "가 방에 입장했습니다.");
                gameMessage.setNickname("[알림]");

            } else if (GameMessage.MessageType.QUIT.equals(gameMessage.getType())) {
                gameMessage.setMessage(gameMessage.getNickname() + "가 방에서 나갔습니다.");
                gameMessage.setNickname("[알림]");

            /** 게임 시작 버튼을 눌렀을 때 매칭 시작 **/
            } else if (GameMessage.MessageType.MATCH.equals(gameMessage.getType())) {
                log.info("매칭 대기");

                String mbti = intToMbti(user.getMbti());

                for (int i = 0; i < 4; i++) {

                }

            /**
             * 매칭을 유저가 수락했을 때
             * 게임 테마 및 랜덤 닉네임 전달
             * */
            } else if (GameMessage.MessageType.ACCEPTED.equals(gameMessage.getType())) {

            } else if (GameMessage.MessageType.VOTED.equals(gameMessage.getType())){

            /** 일반 채팅*/
            } else if (GameMessage.MessageType.TALK.equals(gameMessage.getType())) {

            }
        }

        redisTemplate.convertAndSend(channelTopic.getTopic(), gameMessage);
        log.info("channelTopic {}", channelTopic.getTopic());
    }

    /** db에 int로 저장된 mbti를 알파벳으로 변환*/
    public String intToMbti(int decimal) {
        StringBuilder mbti = new StringBuilder();
        String binaryString = Integer.toBinaryString(decimal);

        if (binaryString.charAt(0) == '0') {
            mbti.append("E");
        } else {
            mbti.append("I");
        }

        if (binaryString.charAt(0) == '0') {
            mbti.append("N");
        } else {
            mbti.append("S");
        }

        if (binaryString.charAt(0) == '0') {
            mbti.append("T");
        } else {
            mbti.append("F");
        }

        if (binaryString.charAt(0) == '0') {
            mbti.append("P");
        } else {
            mbti.append("J");
        }

        return mbti.toString();
    }

}
