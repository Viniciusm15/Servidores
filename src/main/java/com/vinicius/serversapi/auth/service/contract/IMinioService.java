package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.service.impl.MinioService;
import java.util.List;

public interface IMinioService {
    void uploadMultipleFiles(String bucketName, List<MinioService.FileUploadData> files);
    List<String> getPresignedUrls(String bucketName, List<String> fileNames);
}
