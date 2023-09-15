package princessandknights.princesshoppang.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.game.model.GameRoom;
import princessandknights.princesshoppang.game.model.Player;
import princessandknights.princesshoppang.game.repository.GameRoomRepository;
import princessandknights.princesshoppang.user.entity.User;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MatchingService {

    private final GameRoomRepository gameRoomRepository;

    private final Queue<User> eQueue = new LinkedList<>();
    private final Queue<User> iQueue = new LinkedList<>();
    private final Queue<User> nQueue = new LinkedList<>();
    private final Queue<User> sQueue = new LinkedList<>();
    private final Queue<User> tQueue = new LinkedList<>();
    private final Queue<User> fQueue = new LinkedList<>();
    private final Queue<User> pQueue = new LinkedList<>();
    private final Queue<User> jQueue = new LinkedList<>();

    public GameRoom.TitleType startMatching(User user, String mbti) {
        log.info("{}인 {}님의 매칭이 시작되었습니다.", mbti, user.getNickname());

        // 대기방을 subscribe
//        chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.ENTER).roomId("MATCHING").userId(user.getUserId()).build());

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

        GameRoom.TitleType titleType = checkMatching();

        return titleType;
    }

    /** 플레이 가능 인원수가 모였는지 확인 */
    public GameRoom.TitleType checkMatching() {
        log.info("플레이 가능 인원수가 모였는지 확인하고 있습니다.");

        if(eQueue.size() >= 6 && iQueue.size() >= 1) {
            return GameRoom.TitleType.EI;
        } else if (iQueue.size() >= 6 && eQueue.size() >= 1) {
            return GameRoom.TitleType.IE;
        } else if (nQueue.size() >= 6 && sQueue.size() >= 1) {
            return GameRoom.TitleType.NS;
        } else if (sQueue.size() >= 6 && nQueue.size() >= 1) {
            return GameRoom.TitleType.SN;
        } else if (tQueue.size() >= 6 && fQueue.size() >= 1) {
            return GameRoom.TitleType.TF;
        } else if (fQueue.size() >= 6 && tQueue.size() >= 1) {
            return GameRoom.TitleType.FT;
        } else if (pQueue.size() >= 6 && jQueue.size() >= 1) {
            return GameRoom.TitleType.PJ;
        } else if (jQueue.size() >= 6 && pQueue.size() >= 1) {
            return GameRoom.TitleType.JP;
        }

        return GameRoom.TitleType.NOMATCH;
    }



    /** GameRoom 만들기*/
    public GameRoom match(GameRoom.TitleType titleType) {

        Player[] players = new Player[7];
        Long[] userIds = new Long[7];
        Integer[] colors = randomNickname(7);


        // 게임 타이틀 설정
        String title = titleType.getKrTitle();

        if (GameRoom.TitleType.EI.equals(titleType)) {
            // E인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = eQueue.poll().getUserId();
            }
            // I인 유저 1명
            userIds[6] = iQueue.poll().getUserId();

        } else if (GameRoom.TitleType.IE.equals(titleType)) {
            // I인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = iQueue.poll().getUserId();
            }
            // E인 유저 1명
            userIds[6] = eQueue.poll().getUserId();

        } else if (GameRoom.TitleType.IE.equals(titleType)) {
            // I인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = iQueue.poll().getUserId();
            }
            // E인 유저 1명
            userIds[6] = eQueue.poll().getUserId();

        } else if (GameRoom.TitleType.IE.equals(titleType)) {
            // I인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = iQueue.poll().getUserId();
            }
            // E인 유저 1명
            userIds[6] = eQueue.poll().getUserId();

        } else if (GameRoom.TitleType.NS.equals(titleType)) {
            // N인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = nQueue.poll().getUserId();
            }
            // S인 유저 1명
            userIds[6] = sQueue.poll().getUserId();

        } else if (GameRoom.TitleType.SN.equals(titleType)) {
            // S인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = sQueue.poll().getUserId();
            }
            // N인 유저 1명
            userIds[6] = nQueue.poll().getUserId();

        } else if (GameRoom.TitleType.TF.equals(titleType)) {
            // T인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = tQueue.poll().getUserId();
            }
            // F인 유저 1명
            userIds[6] = fQueue.poll().getUserId();

        } else if (GameRoom.TitleType.FT.equals(titleType)) {
            // F인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = fQueue.poll().getUserId();
            }
            // T인 유저 1명
            userIds[6] = tQueue.poll().getUserId();

        } else if (GameRoom.TitleType.PJ.equals(titleType)) {
            // P인 유저 6명
            for (int i = 0; i < 6; i++) {
                userIds[i] = pQueue.poll().getUserId();
            }
            // J인 유저 1명
            userIds[6] = jQueue.poll().getUserId();

        } else if (GameRoom.TitleType.JP.equals(titleType)) {
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

        return gameRoomRepository.createGameRoom(title, players);
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

