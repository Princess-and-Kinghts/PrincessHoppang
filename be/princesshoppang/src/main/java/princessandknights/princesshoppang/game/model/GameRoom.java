package princessandknights.princesshoppang.game.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter

public class GameRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String title;
    private long userCount; // 채팅방 인원수

    public static GameRoom create(String title) {
        GameRoom chatRoom = new GameRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.title = title;
        return chatRoom;
    }

    @Override
    public String toString() {
        return "ChatRoom [roomId=" + roomId + ", name=" + title + ", userCount=" + userCount + "]";
    }
}

