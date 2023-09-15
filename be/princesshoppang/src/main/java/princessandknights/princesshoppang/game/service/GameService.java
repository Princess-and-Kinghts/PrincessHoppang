package princessandknights.princesshoppang.game.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    /** db에 int로 저장된 mbti를 알파벳으로 변환*/
    public String intToMbti(int decimal) {
        StringBuilder mbti = new StringBuilder();
        String binaryString = Integer.toBinaryString(decimal);

        if (binaryString.charAt(0) == '0') {
            mbti.append("E");
        } else {
            mbti.append("I");
        }

        if (binaryString.charAt(0) == '0') {
            mbti.append("N");
        } else {
            mbti.append("S");
        }

        if (binaryString.charAt(0) == '0') {
            mbti.append("T");
        } else {
            mbti.append("F");
        }

        if (binaryString.charAt(0) == '0') {
            mbti.append("P");
        } else {
            mbti.append("J");
        }

        return mbti.toString();
    }
}
