package com.greneta.generatorwebserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greneta.generatorwebserver.constant.ApiUrl;
import com.greneta.generatorwebserver.dto.GeneratorRequestDto;
import com.greneta.generatorwebserver.dto.GeneratorDto;
import com.greneta.generatorwebserver.dto.HttpResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Disabled
class HttpClientServiceTest {

    @Autowired
    HttpClientService service;

    @Autowired
    ObjectMapper mapper;

    Long testModelId;

    @AfterEach
    public void afterEach() {
        if (testModelId != null) {
            service.deleteModelRequest(testModelId, ApiUrl.BASE_LOCAL_URL.getUrl());
        }
    }


    @Test
    @DisplayName("Generate POST HTTP 통신 테스트")
    void givenRequestDtoWhenGeneratePostRequestThenReturnHttpResponseDtoStatusOk() {
        //Given
        GeneratorRequestDto requestDto = createObjRequestDto();
        HttpResponseDto httpResponseDto = service.generateModelPostRequest(requestDto, ApiUrl.BASE_LOCAL_URL.getUrl());
        //When
        GeneratorDto dto = GeneratorDto.convertData(httpResponseDto);
        this.testModelId = dto.getBlenderModelId();
        //Then
        assertThat(dto.getExtension()).isEqualTo(requestDto.getExtension());
        assertThat(httpResponseDto.getBaseFullUri()).isEqualTo(dto.getBaseFullUri());
    }
    private GeneratorRequestDto createObjRequestDto() {
        return GeneratorRequestDto.builder()
                .width(1)
                .depth(1)
                .height(1)
                .upperFloor(1)
                .ringFrequency(1)
                .gapFrequency(1)
                .blankWallSelector(1)
                .extension(".obj")
                .build();
    }


}