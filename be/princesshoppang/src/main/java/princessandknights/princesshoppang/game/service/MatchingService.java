package princessandknights.princesshoppang.game.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.game.model.GameMessage;
import princessandknights.princesshoppang.game.model.MatchResult;
import princessandknights.princesshoppang.game.model.Player;
import princessandknights.princesshoppang.user.entity.User;


import java.util.*;

@RequiredArgsConstructor
@Service
public class MatchingService {

    private final Queue<User> eQueue = new LinkedList<>();
    private final Queue<User> iQueue = new LinkedList<>();
    private final Queue<User> nQueue = new LinkedList<>();
    private final Queue<User> sQueue = new LinkedList<>();
    private final Queue<User> tQueue = new LinkedList<>();
    private final Queue<User> fQueue = new LinkedList<>();
    private final Queue<User> pQueue = new LinkedList<>();
    private final Queue<User> jQueue = new LinkedList<>();

    public void addToQueue(User user, String mbti) {

        if (mbti.charAt(0) =='E') {
            eQueue.add(user);
        } else {
            iQueue.add(user);
        }

        if (mbti.charAt(1) =='N') {
            nQueue.add(user);
        } else {
            sQueue.add(user);
        }

        if (mbti.charAt(2) =='T') {
            tQueue.add(user);
        } else {
            fQueue.add(user);
        }

        if (mbti.charAt(3) =='P') {
            pQueue.add(user);
        } else {
            jQueue.add(user);
        }

        Player[] players = checkMatch();

        if (players.length > 0){

        }
    }

    /** 플레이 가능 인원수가 모였는지 확인 */
    public Player[] checkMatch() {

        if(eQueue.size() >= 6 && iQueue.size() >= 1) {
            return match(MatchResult.TitleType.EI);
        } else if (iQueue.size() >= 6 && eQueue.size() >= 1) {
            return match(MatchResult.TitleType.IE);
        } else if (nQueue.size() >= 6 && sQueue.size() >= 1) {
            return match(MatchResult.TitleType.NS);
        } else if (sQueue.size() >= 6 && nQueue.size() >= 1) {
            return match(MatchResult.TitleType.SN);
        } else if (tQueue.size() >= 6 && fQueue.size() >= 1) {
            return match(MatchResult.TitleType.TF);
        } else if (fQueue.size() >= 6 && tQueue.size() >= 1) {
            return match(MatchResult.TitleType.FT);
        } else if (pQueue.size() >= 6 && jQueue.size() >= 1) {
            return match(MatchResult.TitleType.PJ);
        } else if (jQueue.size() >= 6 && pQueue.size() >= 1
        ) {
            return match(MatchResult.TitleType.JP);
        }

        return new Player[0];
    }

    /** 플레이 가능 인원수가 모였을 때 매치*/
    public Player[] match(MatchResult.TitleType titleType){

        MatchResult matchResult = new MatchResult();
        Player[] players = new Player[7];
        Long[] userIds = new Long[7];
        Integer[] colors = randomNickname(7);

        // 게임 타이틀 설정
        matchResult.setTitle(titleType.getKrTitle());

        if (MatchResult.TitleType.EI.equals(titleType)) {
            // E인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = eQueue.poll().getUserId();
            }
            // I인 유저 1명
            userIds[6] = iQueue.poll().getUserId();

        } else if (MatchResult.TitleType.IE.equals(titleType)) {
            // I인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = iQueue.poll().getUserId();
            }
            // E인 유저 1명
            userIds[6] = eQueue.poll().getUserId();

        } else if (MatchResult.TitleType.IE.equals(titleType)) {
            // I인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = iQueue.poll().getUserId();
            }
            // E인 유저 1명
            userIds[6] = eQueue.poll().getUserId();

        } else if (MatchResult.TitleType.IE.equals(titleType)) {
            // I인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = iQueue.poll().getUserId();
            }
            // E인 유저 1명
            userIds[6] = eQueue.poll().getUserId();

        } else if (MatchResult.TitleType.NS.equals(titleType)) {
            // N인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = nQueue.poll().getUserId();
            }
            // S인 유저 1명
            userIds[6] = sQueue.poll().getUserId();

        } else if (MatchResult.TitleType.SN.equals(titleType)) {
            // S인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = sQueue.poll().getUserId();
            }
            // N인 유저 1명
            userIds[6] = nQueue.poll().getUserId();

        } else if (MatchResult.TitleType.TF.equals(titleType)) {
            // T인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = tQueue.poll().getUserId();
            }
            // F인 유저 1명
            userIds[6] = fQueue.poll().getUserId();

        } else if (MatchResult.TitleType.FT.equals(titleType)) {
            // F인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = fQueue.poll().getUserId();
            }
            // T인 유저 1명
            userIds[6] = tQueue.poll().getUserId();

        } else if (MatchResult.TitleType.PJ.equals(titleType)) {
            // P인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = pQueue.poll().getUserId();
            }
            // J인 유저 1명
            userIds[6] = jQueue.poll().getUserId();

        } else if (MatchResult.TitleType.JP.equals(titleType)) {
            // J인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = jQueue.poll().getUserId();
            }
            // P인 유저 1명
            userIds[6] = pQueue.poll().getUserId();
        }

        for (int i = 0; i < 7; i++) {
            Player player = Player.createPlayer(userIds[i], colors[i]);
            players[i] = player;
        }

        return players;
    }

    /** 인게임 랜덤 닉네임 배정 */
    private Integer[] randomNickname(int playerCount) {

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            numbers.add(i);
        }

        // 리스트를 무작위로 섞음
        Collections.shuffle(numbers);

        Integer[] random = numbers.toArray(new Integer[0]);

        return random;
    }

}
