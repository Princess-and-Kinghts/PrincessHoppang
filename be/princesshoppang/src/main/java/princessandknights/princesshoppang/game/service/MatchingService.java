package princessandknights.princesshoppang.game.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.user.entity.User;


import java.util.*;

@RequiredArgsConstructor
@Service
public class MatchingService {

    private Queue<User> E = new new LinkedList<>();
    private Queue<User> I = new new LinkedList<>();
    private Queue<User> N = new HashMap<>();
    private Queue<User> S = new HashMap<>();
    private Queue<User> T = new HashMap<>();
    private Queue<User> F = new HashMap<>();
    private Queue<User> P = new HashMap<>();
    private Queue<User> J = new HashMap<>();

    public void startMatching(Long userId, String mbti) {
        if (mbti.charAt(0) =='E') {
            E.
        }

    }

}
