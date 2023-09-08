package princessandknights.princesshoppang.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 로컬 저장시 임시 코드
    private String resourcePath = "/upload/**";  // view에서의 접근경로(여기서 접근하면)
    private String savePath = "file:///C:/springboot_img/"; // 실제 파일 저장 경로(spring이 찾아줌)


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }

}
