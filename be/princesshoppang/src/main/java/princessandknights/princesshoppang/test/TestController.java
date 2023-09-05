package princessandknights.princesshoppang.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@Controller
public class TestController {
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        log.info("GET 테스트");
        return "GET 테스트";
    }
}
