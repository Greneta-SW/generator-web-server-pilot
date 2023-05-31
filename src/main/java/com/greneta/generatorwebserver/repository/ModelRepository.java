package com.greneta.generatorwebserver.repository;

import com.greneta.generatorwebserver.entity.GeneratorModel;

import java.util.Optional;


public interface ModelRepository {

    GeneratorModel saveBlenderModel(GeneratorModel model);

    Optional<GeneratorModel> findBlenderModelById(Long blenderModelId);

    void deleteBlenderModelById(Long blenderModelId);

}
