package com.greneta.generatorwebserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class GeneratorRequestDto {

    @NotNull
    Integer width;

    @NotNull
    Integer depth;

    @NotNull
    Integer height;

    @NotNull
    Integer upperFloor;

    @NotNull
    Integer ringFrequency;

    @NotNull
    Integer gapFrequency;

    Integer blankWallSelector;

    @NotNull
    String extension;

    @NotNull
    Boolean generateCheck;

    Long prevModelId;

}
