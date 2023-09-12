package princessandknights.princesshoppang.game.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class GameMessage {

    public GameMessage() {
    }

    @Builder
    public GameMessage(MessageType type, String roomId, String sender, Long userId, Object data) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.userId = userId;
        this.data = data;
    }

    // 메시지 타입 : 입장, 퇴장, 채팅, 투표
    public enum MessageType {
        MATCH, MATCHED, ACCEPTED, ENTER, START, SUGGESTION, VOTE, VOTED, TALK, QUIT, END
    }
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender;
    private Long userId; // 메시지 보낸사람 userId
    private Object data;



    @Override
    public String toString() {
        return "ChatMessage [type=" + type + ", roomId=" + roomId + ", sender=" + sender +", userId=" + userId
                + ", data=" + data + "]";
    }

}
