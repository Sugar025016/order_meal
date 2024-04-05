package com.order_meal.order_meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order_meal.order_meal.entity.FileData;


@Repository
public interface IFileDateRepository extends JpaRepository<FileData,Integer> {

    @Override
    FileData getOne(Integer fileDataId);
}
