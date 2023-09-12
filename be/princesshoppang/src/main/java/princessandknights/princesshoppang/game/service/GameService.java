package princessandknights.princesshoppang.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.game.model.GameMessage;
import princessandknights.princesshoppang.webSocket.model.ChatMessage;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;


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
