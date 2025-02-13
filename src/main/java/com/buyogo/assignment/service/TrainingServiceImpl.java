package com.buyogo.assignment.service;

import com.buyogo.assignment.dao.TrainingRepository;
import com.buyogo.assignment.entity.TrainingCenter;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<TrainingCenter> findAll() {
        logger.info("Fetching all training centers...");
        List<TrainingCenter> trainingCenters = trainingRepository.findAll();
        return trainingCenters;
    }

    @Override
    public TrainingCenter findById(int theId) {
        logger.info("Fetching training center with ID: {}", theId);
        Optional<TrainingCenter> result = trainingRepository.findById(theId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Did not find training center with id - " + theId);
        }
    }

    @Override
    public TrainingCenter save(TrainingCenter trainingCenter) {
        logger.info("Saving new training center: {}", trainingCenter);
        // Set the createdOn timestamp before saving
        trainingCenter.setCreatedOn(System.currentTimeMillis());
        TrainingCenter savedTrainingCenter = trainingRepository.save(trainingCenter);
        return savedTrainingCenter;
    }

    @Override
    public void deleteById(int theId) {
        logger.info("Attempting to delete training center with ID: {}", theId);
        if (!trainingRepository.existsById(theId)) {
            throw new EntityNotFoundException("Training with ID " + theId + " not found.");
        }
        trainingRepository.deleteById(theId);
    }

    @Override
    public void deleteAll() {
        trainingRepository.deleteAll();
        logger.info("All training centers deleted successfully");
    }
}
