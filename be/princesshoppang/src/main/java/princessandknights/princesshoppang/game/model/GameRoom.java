package princessandknights.princesshoppang.game.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GameRoom {
    private static final long serialVersionUID = 6494678977089006639L;
    private String channelId;
    private String title;
    private Player[] players;
    private long userCount; // 채팅방 인원수

    public static GameRoom create(String title, Player[] players) {
        GameRoom gameRoom = new GameRoom();
        gameRoom.channelId = UUID.randomUUID().toString();
        gameRoom.title = title;
        gameRoom.players = players;
        return gameRoom;
    }

    public enum TitleType {
        EI("E 속에 숨은 I 찾기"),
        IE("I 속에 숨은 E 찾기"),
        NS("N 속에 숨은 S 찾기"),
        SN("S 속에 숨은 N 찾기"),
        TF("T 속에 숨은 F 찾기"),
        FT("F 속에 숨은 T 찾기"),
        PJ("P 속에 숨은 J 찾기"),
        JP("J 속에 숨은 P 찾기"),
        NOMATCH("매치되지 않았습니다.")
        ;

        private String krTitle;
        TitleType(String krTitle) {
            this.krTitle = krTitle;
        }

        public String getKrTitle() {
            return krTitle;
        }
    }
}
