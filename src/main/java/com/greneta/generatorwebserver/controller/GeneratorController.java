package com.greneta.generatorwebserver.controller;

import com.greneta.generatorwebserver.dto.GeneratorRequestDto;
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

    @GetMapping()
    public ModelAndView home(
    ) {


        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", "Test"
        )
        );
    }

    @PostMapping
    public ModelAndView modelGenerate(
            @ModelAttribute GeneratorRequestDto requestDto
    ) {

        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", "Test"
        )
        );

    }

    @DeleteMapping("/{modelId}")
    public ModelAndView modelDelete(
            @PathVariable Long modelId
    ) {

        return new ModelAndView(
                "generator/home"
                , Map.of(
                "response", "Test"
        )
        );

    }
}
