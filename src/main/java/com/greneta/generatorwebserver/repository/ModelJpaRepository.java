package com.greneta.generatorwebserver.repository;

import com.greneta.generatorwebserver.entity.GeneratorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelJpaRepository extends JpaRepository<GeneratorModel, Long> {
}
