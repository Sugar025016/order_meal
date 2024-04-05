package com.order_meal.order_meal.service;

import org.springframework.web.multipart.MultipartFile;

import com.order_meal.order_meal.entity.FileData;

public interface IFileService {
    


    FileData getFileById(Integer imgId);

    FileData save(MultipartFile multipartFile);

    String getImgUrl();


}
