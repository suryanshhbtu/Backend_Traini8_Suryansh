package com.buyogo.assignment.service;

import com.buyogo.assignment.entity.TrainingCenter;

import java.util.List;

public interface TrainingService {


    List<TrainingCenter> findAll();

    TrainingCenter save(TrainingCenter trainingCenter);

    TrainingCenter findById(int id);

    void deleteById(int theId);

    void deleteAll();

}
