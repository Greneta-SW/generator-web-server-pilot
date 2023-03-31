package com.greneta.generatorwebserver.controller;

import com.greneta.generatorwebserver.dto.GeneratorRequestDto;
import com.greneta.generatorwebserver.dto.ModelResponseDto;
import com.greneta.generatorwebserver.service.GeneratorService;
import com.greneta.generatorwebserver.service.RequestUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/generator")
@Validated
public class GeneratorController {

    private final GeneratorService generateService;

    private final RequestUrlService urlService;

    @GetMapping()
    public ModelAndView home(
    ) {
        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", createBaseResponse()
        )
        );
    }

    @PostMapping
    public ModelAndView modelGenerate(
            @ModelAttribute GeneratorRequestDto requestDto
    ) {
        String requestUrl = urlService.getRequestUrl();
        ModelResponseDto modelResponseDto = generateService.generateModel(requestDto, requestUrl);
        log.info("ModelResponseDto BaseFileName = {}", modelResponseDto.getBaseFileName());
        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", modelResponseDto
        )
        );

    }

    @PostMapping("/init-url")
    public ModelAndView initUrl(
            @RequestParam String requestUrl
    ) {
        Boolean aBoolean = urlService.setUrl(requestUrl);

        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", createBaseResponse()));

    }

    @RequestMapping("/reset-url")
    public ModelAndView resetUrl(
    ) {
        Boolean aBoolean = urlService.deleteUrl();
        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", createBaseResponse()
        ));

    }

    @RequestMapping("/{modelId}")
    public ModelAndView modelDelete(
            @PathVariable Long modelId
    ) {
        String requestUrl = urlService.getRequestUrl();
        generateService.deleteModel(modelId, requestUrl);
        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", createBaseResponse()
        )
        );
    }

    private ModelResponseDto createBaseResponse() {
        Boolean aBoolean = urlService.isEmptyUrl();
        if (aBoolean) {
            return ModelResponseDto.builder()
                    .baseFileName("326ce6af-39e2-4527-8292-1022043c5d00")
                    .extension(".gltf")
                    .generateCheck(false)
                    .initCheck(false)
                    .build();
        } else {
            return ModelResponseDto.builder()
                    .baseFileName("326ce6af-39e2-4527-8292-1022043c5d00")
                    .extension(".gltf")
                    .generateCheck(false)
                    .initCheck(true)
                    .build();
        }

    }

}
