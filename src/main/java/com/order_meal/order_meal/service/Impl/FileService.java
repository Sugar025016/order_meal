package com.order_meal.order_meal.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.order_meal.order_meal.entity.FileData;
import com.order_meal.order_meal.repository.IFileDateRepository;
import com.order_meal.order_meal.service.IFileService;

@Transactional
@Service
public class FileService implements IFileService {

    @Autowired
    IFileDateRepository iFileDateRepository;

    @Value("${uploadFilePath}")
    String uploadImgUrl;

    @Value("${filePath}")
    String getImgUrl;


    @Override
    public FileData getFileById(Integer imgId) {
        Optional<FileData> fileOptional = iFileDateRepository.findById(imgId);

        FileData fileData = fileOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "file not found"));

        return fileData;
    }


    @Override
    public FileData save(MultipartFile multipartFile) {
        // String[] suffix = multipartFile.getOriginalFilename().split(".");
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null) {
            throw new NullPointerException("originalFilename is null");
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        Date date = new Date();
        Long round = Math.round(Math.random() * 10000);
        Long time = date.getTime();
        String fileName = String.valueOf(time) + round;
        // String filePath = imagePutUrl+"/"+fileName+suffix;
        System.out.println(uploadImgUrl);
        String filePath = uploadImgUrl + fileName + suffix;
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileData fileData = new FileData(multipartFile, suffix, fileName);
        FileData save = iFileDateRepository.save(fileData);

        return save;
    }


    @Override
    public String getImgUrl() {
        return getImgUrl;
    }

}
