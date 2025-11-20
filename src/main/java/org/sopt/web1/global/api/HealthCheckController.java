package org.sopt.web1.global.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "HealthCheck", description = "서버 상태 확인 API")
public class HealthCheckController {

    @Value("${server.env}")
    private String serverEnv;

    // green인지 blue인지 확인
    @Operation(summary = "배포 버전 확인", description = "현재 실행 중인 프로필(blue, green, default)을 반환합니다.")
    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return Map.of("env", serverEnv);
    }
}
