package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.personPhoto.PersonPhotoResponseDto;
import com.vinicius.serversapi.auth.mapper.PersonPhotoMapper;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.model.core.PersonPhoto;
import com.vinicius.serversapi.auth.repository.PersonPhotoRepository;
import com.vinicius.serversapi.auth.repository.PersonRepository;
import com.vinicius.serversapi.auth.service.contract.IMinioService;
import com.vinicius.serversapi.auth.service.contract.IPersonPhotoService;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PersonPhotoService implements IPersonPhotoService {

    private final PersonPhotoRepository repository;
    private final PersonRepository personRepository;
    private final PersonPhotoMapper mapper;
    private final IMinioService minioService;

    @Value("${minio.bucket.photos}")
    private String photoBucket;

    public List<PersonPhotoResponseDto> upload(Long personId, List<MultipartFile> files) {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        List<String> hashes = files.stream()
                .map(f -> UUID.randomUUID().toString())
                .toList();

        List<MinioService.FileUploadData> uploadData =
                zipHashesAndFiles(hashes, files);

        minioService.uploadMultipleFiles(photoBucket, uploadData);

        return hashes.stream().map(hash -> {
            var photo = PersonPhoto.builder()
                    .person(person)
                    .bucket(photoBucket)
                    .hash(hash)
                    .date(LocalDate.now())
                    .build();

            var savedPhoto = repository.save(photo);
            var url = minioService.getPresignedUrls(photoBucket, List.of(hash)).stream().findFirst().orElse(null);
            return mapper.toDto(savedPhoto, url);
        }).toList();
    }

    private List<MinioService.FileUploadData> zipHashesAndFiles(List<String> hashes, List<MultipartFile> files) {
        if (hashes.size() != files.size()) throw new IllegalArgumentException("Tamanhos diferentes");
        return IntStream.range(0, files.size())
                .mapToObj(i -> new MinioService.FileUploadData(hashes.get(i), files.get(i)))
                .toList();
    }

    public PersonPhotoResponseDto getById(Long id) {
        var photo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Foto não encontrada"));

        var url = minioService.getPresignedUrls(photoBucket, List.of(photo.getHash())).stream().findFirst().orElse(null);
        return mapper.toDto(photo, url);
    }

    public Page<PersonPhotoResponseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(photo -> {
            var url = minioService.getPresignedUrls(photoBucket, List.of(photo.getHash())).stream().findFirst().orElse(null);
            return mapper.toDto(photo, url);
        });
    }

    public void delete(Long id) {
        var photo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Foto não encontrada"));
        photo.setDeleted(true);
        repository.save(photo);
    }

    @Override
    public String getPersonPhotoUrl(Person person) {
        if (person.getPhotos() == null || person.getPhotos().isEmpty()) {
            return null;
        }

        return person.getPhotos().stream()
                .findFirst()
                .map(photo -> {
                    if (photo.getHash() != null) {
                        List<String> presignedUrls = minioService.getPresignedUrls(photoBucket, List.of(photo.getHash()));
                        return presignedUrls.isEmpty() ? null : presignedUrls.get(0);
                    }
                    return null;
                })
                .orElse(null);
    }
}
