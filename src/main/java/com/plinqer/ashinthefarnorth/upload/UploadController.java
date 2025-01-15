package com.plinqer.ashinthefarnorth.upload;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.plinqer.ashinthefarnorth.upload.dto.UploadFileResponse;
import com.plinqer.ashinthefarnorth.upload.service.UploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UploadController implements UploadApi {

    private final UploadService uploadService;

    @Override
    public ResponseEntity<UploadFileResponse> uploadFile(MultipartFile file) {
        UploadFileResponse response = uploadService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<UploadFileResponse>> uploadFiles(List<MultipartFile> files) {
        List<UploadFileResponse> response = uploadService.uploadFiles(files);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);    }
}
