package princessandknights.princesshoppang.game.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.game.model.GameRoom;
import princessandknights.princesshoppang.game.model.Player;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class GameRoomRepository {
    private Map<String, GameRoom> roomInfo = new HashMap<>();

    private Map<String , LocalDateTime> startTime = new HashMap<>();

    private Map<String, Integer> playerCount = new HashMap<>();
//    private Map<String, Map<>>


    public GameRoom createGameRoom(String title, Player[] players) {

        GameRoom gameRoom = GameRoom.create(title, players);

        roomInfo.put(gameRoom.getChannelId(), gameRoom);
        playerCount.put(gameRoom.getChannelId(), 0);

        log.info("게임방을 생성했습니다.");

        return gameRoom;
    }

    public int addPlayerCount(String channelId){
        playerCount.put(channelId, playerCount.get(channelId) + 1);

        return playerCount.get(channelId);
    }

    public GameRoom getRoomInfo(String channelId) {
        return roomInfo.get(channelId);
    }

    public void updateStartTime(String channelId) {
        LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        startTime.put(channelId, time);
    }
//    public GameRoom addPlayerCount(String gameRoomId){
//        playerCount.put(gameRoomId, playerCount.get(gameRoomId) + 1);
//        if (playerCount.get(gameRoomId) == 7) {
//            // 게임 시작 시간 기록
//            LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//            startTime.put(gameRoomId, time);
//            log.info("addPlayerCount에서 7명 모두 수락했을 때 {}방이 열린다.", roomInfo.get(gameRoomId).toString());
//            return roomInfo.get(gameRoomId);
//        }
//        return null;
//    }
}
