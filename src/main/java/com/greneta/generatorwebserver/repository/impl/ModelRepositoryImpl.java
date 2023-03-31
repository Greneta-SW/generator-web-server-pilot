package com.greneta.generatorwebserver.repository.impl;

import com.greneta.generatorwebserver.entity.GeneratorModel;
import com.greneta.generatorwebserver.repository.ModelJpaRepository;
import com.greneta.generatorwebserver.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ModelRepositoryImpl implements ModelRepository {

    private final ModelJpaRepository jpaRepository;

    @Override
    public GeneratorModel saveBlenderModel(GeneratorModel model) {
        return jpaRepository.save(model);
    }

    @Override
    public Optional<GeneratorModel> findBlenderModelById(Long blenderModelId) {
        return jpaRepository.findById(blenderModelId);
    }

    @Override
    public void deleteBlenderModelById(Long blenderModelId) {
        jpaRepository.deleteById(blenderModelId);
    }


}
