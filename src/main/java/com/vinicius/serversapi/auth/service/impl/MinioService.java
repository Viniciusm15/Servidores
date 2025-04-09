package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.service.contract.IMinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MinioService implements IMinioService {

    private final MinioClient minioClient;

    @Override
    public void uploadMultipleFiles(String bucketName, List<FileUploadData> files) {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            for (FileUploadData fileData : files) {
                MultipartFile file = fileData.file();
                String fileName = fileData.fileName();
                InputStream inputStream = file.getInputStream();

                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .stream(inputStream, inputStream.available(), -1)
                                .contentType(file.getContentType())
                                .build()
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload para o MinIO", e);
        }
    }

    public record FileUploadData(String fileName, MultipartFile file) {}

    @Override
    public List<String> getPresignedUrls(String bucketName, List<String> fileNames) {
        try {
            return fileNames.stream().map(fileName -> {
                try {
                    return minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .bucket(bucketName)
                                    .object(fileName)
                                    .method(Method.GET)
                                    .expiry(5, TimeUnit.MINUTES)  // URL válida por 5 minutos
                                    .build()
                    );
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao gerar URL assinada para o arquivo " + fileName, e);
                }
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URLs assinadas do MinIO", e);
        }
    }
}
