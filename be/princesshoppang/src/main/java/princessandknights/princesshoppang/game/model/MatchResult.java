package princessandknights.princesshoppang.game.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResult {
    String title;
    Player[] players;

    public enum TitleType {
        EI("숨은 E 속에 I 찾기"),
        IE("숨은 I 속에 E 찾기"),
        NS("숨은 N 속에 S 찾기"),
        SN("숨은 S 속에 N 찾기"),
        TF("숨은 T 속에 F 찾기"),
        FT("숨은 F 속에 T 찾기"),
        PJ("숨은 P 속에 J 찾기"),
        JP("숨은 J 속에 P 찾기"),
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
