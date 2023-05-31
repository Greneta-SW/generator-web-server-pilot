package com.greneta.generatorwebserver.dto;


import com.greneta.generatorwebserver.constant.ApiUrl;
import com.greneta.generatorwebserver.entity.GeneratorModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class ModelResponseDto {

    Long modelId;

    String baseFileName;

    Integer width;

    Integer depth;

    Integer height;

    Integer upperFloor;

    Integer ringFrequency;

    Integer vgapFrequency;

    Integer blankWallSelector;

    String extension;

    String nodeGroup;

    boolean generateCheck = false;
    boolean initCheck = false;

    String downloadUrl;

    private ModelResponseDto(GeneratorModel model) {
        this.modelId = model.getModelId();
        this.baseFileName = model.getBaseFileName();
        this.width = model.getWidth();
        this.depth = model.getWidth();
        this.height = model.getHeight();
        this.upperFloor = model.getUpperFloor();
        this.ringFrequency = model.getRingFrequency();
        this.vgapFrequency = model.getVGapFrequency();
        this.blankWallSelector = model.getBlankWallSelector();
        this.extension = model.getExtension();
        this.generateCheck = true;
        this.initCheck = true;
        this.nodeGroup = model.getNodeGroup();
        this.downloadUrl = model.getDownloadUrl();
    }

    public static ModelResponseDto of(GeneratorModel model) {
        return new ModelResponseDto(model);
    }
}
