package princessandknights.princesshoppang.game.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Player {
    private Long userId;
    private ColorType color;

    public enum ColorType {
        RED(0), ORANGE(1), YELLOW(2), GREEN(3), BLUE(4), NAVY(5), PURPLE(6);
        private int colorId;
        ColorType(int colorId) {
            this.colorId = colorId;
        }
        public int getColorId() {
            return colorId;
        }
    }

    public static Player createPlayer(Long userId, int colorId) {
        // colorId로 ColorType 찾기
        ColorType desiredColorType = null;

        Player.ColorType[] colorTypes = Player.ColorType.values();

        for (Player.ColorType colorType : colorTypes) {
            if (colorType.getColorId() == colorId) {
                desiredColorType = colorType;
                break;
            }
        }

        return Player.builder()
                .userId(userId)
                .color(desiredColorType)
                .build();
    }

}
