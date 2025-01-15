package com.plinqer.ashinthefarnorth.upload;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.plinqer.ashinthefarnorth.upload.dto.UploadFileResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Upload", description = "파일 업로드 API")
public interface UploadApi {

    @Operation(summary = "단일 파일 업로드")
    @ApiResponses({
        @ApiResponse(responseCode = "201")
    })
    @PostMapping(
        value = "/upload/file",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UploadFileResponse> uploadFile(
        @RequestPart("file") MultipartFile file
    );

    @Operation(summary = "다중 파일 업로드")
    @ApiResponses({
        @ApiResponse(responseCode = "201")
    })
    @PostMapping(
        value = "/upload/files",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<UploadFileResponse>> uploadFiles(
        @RequestPart("files") List<MultipartFile> files
    );
}
