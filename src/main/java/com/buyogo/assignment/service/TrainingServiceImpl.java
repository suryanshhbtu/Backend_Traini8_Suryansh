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
        logger.info("Fetched {} training centers", trainingCenters.size());
        return trainingCenters;
    }

    @Override
    public TrainingCenter findById(int theId) {
        logger.info("Fetching training center with ID: {}", theId);
        Optional<TrainingCenter> result = trainingRepository.findById(theId);
        if (result.isPresent()) {
            logger.info("Found training center: {}", result.get());
            return result.get();
        } else {
            logger.warn("Training center with ID {} not found", theId);
            throw new RuntimeException("Did not find training center with id - " + theId);
        }
    }

    @Override
    public TrainingCenter save(TrainingCenter trainingCenter) {
        logger.info("Saving new training center: {}", trainingCenter);
        // Set the createdOn timestamp before saving
        trainingCenter.setCreatedOn(System.currentTimeMillis());
        TrainingCenter savedTrainingCenter = trainingRepository.save(trainingCenter);
        logger.info("Training center saved successfully with ID: {}", savedTrainingCenter.getId());
        return savedTrainingCenter;
    }

    @Override
    public void deleteById(int theId) {
        logger.info("Attempting to delete training center with ID: {}", theId);
        if (!trainingRepository.existsById(theId)) {
            logger.warn("Training center with ID {} not found for deletion", theId);
            throw new EntityNotFoundException("Training with ID " + theId + " not found.");
        }
        trainingRepository.deleteById(theId);
        logger.info("Training center with ID {} deleted successfully", theId);
    }

    @Override
    public void deleteAll() {
        logger.info("Deleting all training centers...");
        trainingRepository.deleteAll();
        logger.info("All training centers deleted successfully");
    }
}
