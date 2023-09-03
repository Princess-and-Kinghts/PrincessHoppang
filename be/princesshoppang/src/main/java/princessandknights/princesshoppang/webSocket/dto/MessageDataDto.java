package princessandknights.princesshoppang.webSocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDataDto {
    private String msg;
    private LocalDateTime sentAt;

}

