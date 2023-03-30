package com.greneta.generatorwebserver.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratorDto {

    Long blenderModelId;

    String baseFileName;

    String modelUrl;

    Integer width;

    Integer depth;

    Integer height;

    Integer upperFloor;

    Integer ringFrequency;

    Integer vgapFrequency;

    Integer blankWallSelector;

    String extension;

    String nodeGroup;

    boolean generateCheck = false;

    public static GeneratorDto convertData(HttpResponseDto dto) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String , String > responseDtoData = (Map<String , String >) dto.getData();
        return mapper.convertValue(responseDtoData, GeneratorDto.class);
    }


}
