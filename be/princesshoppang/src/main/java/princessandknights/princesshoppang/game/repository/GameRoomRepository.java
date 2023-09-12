package princessandknights.princesshoppang.game.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.game.model.GameRoom;
import princessandknights.princesshoppang.game.model.Player;

import javax.annotation.Resource;


@Slf4j
@RequiredArgsConstructor
@Service
public class GameRoomRepository {
    // Redis CacheKeys
    private static final String CHAT_ROOMS = "CHAT_ROOM"; // 채팅룸 저장
    public static final String USER_COUNT = "USER_COUNT"; // 채팅룸에 입장한 클라이언트수 저장
    public static final String ENTER_INFO = "ENTER_INFO"; // 채팅룸에 입장한 클라이언트의 sessionId와 채팅룸 id를 맵핑한 정보 저장

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, GameRoom> hashOpsGameRoom;
    private HashOperations<String, String, String> hashOpsChatRoomRegTime;

    public GameRoom createGameRoom(String title, Player[] players) {
        GameRoom gameRoom = GameRoom.create(title, players);
        hashOpsGameRoom.put(CHAT_ROOMS, gameRoom.getRoomId(), gameRoom);

        log.info("{} 게임방을 생성했습니다.", gameRoom.getRoomId());

        return gameRoom;
    }

    public void createMatchingRoom() {
        hashOpsGameRoom.put("CHAT_ROOM", "MATCHING", null);

        log.info("게임방 매칭방을 생성했습니다.");
    }
}
