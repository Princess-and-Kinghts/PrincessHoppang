package princessandknights.princesshoppang;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import princessandknights.princesshoppang.game.repository.GameRoomRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class HPCommandLineRunner implements CommandLineRunner {
    private final GameRoomRepository gameRoomRepository;

    @Override
    public void run(String... args) throws Exception {
        gameRoomRepository.createMatchingRoom();
        // 빌드 시 한 번 실행될 코드를 작성합니다.
        System.out.println("애플리케이션 빌드 시 실행되는 코드");

    }


}
