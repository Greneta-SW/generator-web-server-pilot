package com.greneta.generatorwebserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HttpResponseDto {

    Object data;

    HttpResponseDto(Object data) {
        this.data = data;
    }

    public static HttpResponseDto of(Object data) {
        return new HttpResponseDto(data);
    }
}
