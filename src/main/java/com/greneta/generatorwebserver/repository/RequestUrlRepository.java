package com.greneta.generatorwebserver.repository;

import com.greneta.generatorwebserver.entity.GeneratorModel;
import com.greneta.generatorwebserver.entity.RequestUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestUrlRepository extends JpaRepository<RequestUrl, Long> {
}
