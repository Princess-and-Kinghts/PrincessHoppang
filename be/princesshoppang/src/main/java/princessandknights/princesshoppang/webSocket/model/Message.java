package princessandknights.princesshoppang.webSocket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    public Message() {
    }

    @Builder
    public Message(MessageType type, String channelId, Long userId, Object data, String message) {
        this.type = type;
        this.channelId = channelId;
        this.userId = userId;
        this.data = data;
    }

    public enum MessageType {
        MATCH, MATCHED, ACCEPT, ENTER, START, SUGGESTION, VOTE, VOTED, TALK, QUIT, RESULT, END
    }
    private MessageType type; // 메시지 타입
    private String channelId; // 방번호
    private Long userId; // 메시지 보낸사람 userId
    private Object data;


    @Override
    public String toString() {
        return "ChatMessage [type=" + type + ", channelId=" + channelId + "userId=" + userId
                + ", data=" + data + "]";
    }

}
