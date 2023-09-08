package princessandknights.princesshoppang.game.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import princessandknights.princesshoppang.user.entity.User;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private Queue<User> waitingQueue = new LinkedList<>();
    private Map<String, Integer> mbtiCounts = new HashMap<>();
    private Map<String, ArrayList<User>> mbtiQueue = new HashMap<>();

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        waitingQueue.offer(user);
        mbtiCounts.put(intToMbti(user.getMbti()), mbtiCounts.getOrDefault(user.getMbti(), 0) + 1);
        return ResponseEntity.ok("User added to the waiting queue.");
    }

    @GetMapping("/matchUsers")
    public ResponseEntity<List<String>> matchUsers() {
        List<String> matchedUsers = new ArrayList<>();

        if (mbtiCounts.getOrDefault("E", 0) >= 6 &&
                mbtiCounts.getOrDefault("I", 0) >= 6 &&
                mbtiCounts.getOrDefault("N", 0) >= 6 &&
                mbtiCounts.getOrDefault("S", 0) >= 6 &&
                mbtiCounts.getOrDefault("T", 0) >= 6 &&
                mbtiCounts.getOrDefault("F", 0) >= 6 &&
                mbtiCounts.getOrDefault("P", 0) >= 6 &&
                mbtiCounts.getOrDefault("J", 0) >= 6) {

            for (String mbti : Arrays.asList("E", "I", "N", "S", "T", "F", "P", "J")) {
                for (int i = 0; i < 6; i++) {
                    User user = waitingQueue.poll();
                    if (user != null && user.getMbti().equals(mbti)) {
                        matchedUsers.add(user.getUserId());
                        mbtiCounts.put(mbti, mbtiCounts.get(mbti) - 1);
                    }
                }
            }
        }

        return ResponseEntity.ok(matchedUsers);
    }

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
