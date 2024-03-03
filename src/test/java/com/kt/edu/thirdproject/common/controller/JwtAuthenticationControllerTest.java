package com.kt.edu.thirdproject.common.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAuthenticationToken() throws Exception {
        // 사용자 인증 토큰 생성 테스트
        Map<String, String> input = new HashMap<>();
        input.put("username", "edu");
        input.put("password", "edu1234");

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON) // 요청 타입을 JSON으로 설정
                        .content(objectMapper.writeValueAsString(input))) // Map을 JSON 문자열로 변환하여 내용 전송
                .andExpect(status().isOk()) // HTTP 200 상태 코드 검증
                .andDo(print()); // 응답 내용 출력
    }
}