package com.greneta.generatorwebserver.entity;

import com.greneta.generatorwebserver.dto.GeneratorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class GeneratorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long blenderModelId;


    @Column(nullable = false)
    String baseFileName;


    @Column(nullable = false)
    String modelUrl;

    @Column(nullable = false)
    Integer width;

    @Column(nullable = false)
    Integer depth;

    @Column(nullable = false)
    Integer height;

    @Column(nullable = false)
    Integer upperFloor;

    @Column(nullable = false)
    Integer ringFrequency;

    @Column(nullable = false)
    Integer vGapFrequency;

    @Column(nullable = true)
    Integer blankWallSelector;

    @Column(nullable = false)
    String extension;

    @Column(nullable = false)
    String nodeGroup;


    GeneratorModel(GeneratorDto dto) {
        this.width = dto.getWidth();
        this.depth = dto.getDepth();
        this.height = dto.getHeight();
        this.upperFloor = dto.getUpperFloor();
        this.ringFrequency = dto.getRingFrequency();
        this.vGapFrequency = dto.getVgapFrequency();
        this.blankWallSelector = dto.getBlankWallSelector();
        this.extension = dto.getExtension();
        this.baseFileName = generateFileName();
        this.nodeGroup = dto.getNodeGroup();
    }

    public static GeneratorModel of(GeneratorDto dto) {
        return new GeneratorModel(dto);
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }

}
