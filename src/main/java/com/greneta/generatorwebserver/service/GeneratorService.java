package com.greneta.generatorwebserver.service;


import com.greneta.generatorwebserver.constant.ErrorCode;
import com.greneta.generatorwebserver.dto.GeneratorDto;
import com.greneta.generatorwebserver.dto.GeneratorRequestDto;
import com.greneta.generatorwebserver.dto.HttpResponseDto;
import com.greneta.generatorwebserver.dto.ModelResponseDto;
import com.greneta.generatorwebserver.entity.GeneratorModel;
import com.greneta.generatorwebserver.exception.ServiceLogicException;
import com.greneta.generatorwebserver.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GeneratorService {

    private final HttpClientService httpClientService;

    private final ModelRepository modelRepository;


    public ModelResponseDto generateModel(GeneratorRequestDto dto, String requestUrl) {
        HttpResponseDto httpDto = httpClientService.generateModelPostRequest(dto, requestUrl);
        GeneratorDto generatorDto = GeneratorDto.convertData(httpDto);
        log.info("GeneratorDto BaseFileName = {}",generatorDto.getBaseFileName());
        GeneratorModel model = GeneratorModel.of(generatorDto);
        log.info("GeneratorModel BaseFileName = {}",model.getBaseFileName());
        GeneratorModel savaModel = modelRepository.saveBlenderModel(model);
        log.info("GeneratorSaveModel BaseFileName = {}",savaModel.getBaseFileName());

        return ModelResponseDto.of(savaModel);
    }

    public void deleteModel(Long modelId, String requestUrl) {
        GeneratorModel generatorModel = verifiedModelById(modelId);
        httpClientService.deleteModelRequest(generatorModel.getBlenderServerModelId(),requestUrl);
        modelRepository.deleteBlenderModelById(generatorModel.getModelId());
    }

    private GeneratorModel verifiedModelById(Long modelId) {
        return modelRepository.findBlenderModelById(modelId)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.MODEL_NOT_FOUND));
    }
}
