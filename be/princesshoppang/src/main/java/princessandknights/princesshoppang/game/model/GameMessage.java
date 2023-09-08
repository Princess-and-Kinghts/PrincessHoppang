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
    public GameMessage(MessageType type, String roomId, Long userId, String nickname, String message, Object data) {
        this.type = type;
        this.roomId = roomId;
        this.userId = userId;
        this.nickname = nickname;
        this.message = message;
        this.data = data;
    }

    // 메시지 타입 : 입장, 퇴장, 채팅, 투표
    public enum MessageType {
        MATCH, MATCHED, ACCEPTED, ENTER, START, SUGGESTION, VOTE, VOTED, TALK, QUIT, END
    }
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private Long userId; // 메시지 보낸사람 userId
    private String nickname; // 메시지 보낸사람 닉네임
    private String message; // 메시지
    private Object data;
//    private long userCount; // 채팅방 인원수, 채팅방 내에서 메시지가 전달될때 인원수 갱신시 사용
//    private String imgUrl;


    @Override
    public String toString() {
        return "ChatMessage [type=" + type + ", roomId=" + roomId + ", sender=" + sender + ", message=" + message
                + ", data=" + data + "]";
    }

}
