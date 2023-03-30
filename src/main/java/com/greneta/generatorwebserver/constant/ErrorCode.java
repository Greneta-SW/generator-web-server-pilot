package com.greneta.generatorwebserver.constant;

import lombok.Getter;

public enum ErrorCode {

    BAD_REQUEST(400, "BAD REQUEST"),
    AWS_BAD_REQUEST(400, "BAD REQUEST"),
    ARGUMENT_MISMATCH_BAD_REQUEST(400, "Argument Mismatch Bad Request" ),
    ACCESS_DENIED(403, "ACCESS DENIED"),
    NOT_FOUND(404, "NOT FOUND"),
    AWS_FILE_NOT_FOUND(404, "AWS File NOT FOUND"),
    BLENDER_MODEL_NOT_FOUND(404, "Blender Model Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error" ),
    DATA_ACCESS_ERROR(500, "Data Access Error"),

    BLENDER_PROCESS_IO_ERROR(500, "Starting Blender Process I/O Error!"),
    AWS_IO_ERROR(500, "AWS S3 I/O Error!"),

    BLENDER_PROCESS_INTERRUPTED_ERROR(500, "Starting Blender Process Interrupted Error!"),

    BLENDER_PROCESS_ERROR(500, "Starting Blender Process Error!"),
    AWS_S3_UPLOAD_ERROR(500, "AWS S3 Upload Error!"),
    AWS_S3_DOWNLOAD_ERROR(500, "AWS S3 Download Error!"),
    AWS_S3_DELETE_ERROR(500, "AWS S3 Delete Error!"),
    NOT_IMPLEMENTED(501,"NOT IMPLEMENTED");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ErrorCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
