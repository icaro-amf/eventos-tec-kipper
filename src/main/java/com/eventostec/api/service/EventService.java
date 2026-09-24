package com.eventostec.api.service;

import com.eventostec.api.domain.event.Event;
import com.eventostec.api.dto.event.EventRequestDTO;
import com.eventostec.api.repositories.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class EventService {
    @Value("${aws.bucket.name}")
    private String bucketname;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private EventRepository repository;

    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if (!data.image().isEmpty()) {
            imgUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        return newEvent;
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultipartFile(multipartFile);
            s3Client.putObject(PutObjectRequest.builder().bucket(bucketname).key(filename).build(), RequestBody.fromFile(file));
            file.delete();
            return s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucketname).key(filename).build()).toString();
        } catch (Exception e) {
            log.error("Erro ao subir imagem para o bucket", e);
            return "";
        }
    }

    private File convertMultipartFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
