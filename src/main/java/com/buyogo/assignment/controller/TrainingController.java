package com.buyogo.assignment.controller;

import com.buyogo.assignment.entity.TrainingCenter;
import com.buyogo.assignment.service.TrainingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
public class TrainingController {

	private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);
	private final TrainingService trainingService;

	public TrainingController(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@GetMapping("/list")
	public ResponseEntity<List<TrainingCenter>> listTrainingCenters() {
		logger.info("Fetching all training centers...");
		List<TrainingCenter> trainingCenters = trainingService.findAll();
		logger.info("Fetched {} training centers", trainingCenters.size());
		return new ResponseEntity<>(trainingCenters, HttpStatus.OK);
	}

	@GetMapping("/findById")
	public ResponseEntity<TrainingCenter> findCenterById(@RequestParam("id")  int id) {
		logger.info("Fetching training center with ID: {}", id);
		TrainingCenter trainingCenter = trainingService.findById(id);
		if (trainingCenter == null) {
			logger.warn("Training center with ID {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info("Training center found: {}", trainingCenter);
		return new ResponseEntity<>(trainingCenter, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<TrainingCenter> addTrainingCenterRequest(@Valid @RequestBody TrainingCenter trainingCenter) {
		logger.info("Saving new training center: {}", trainingCenter);
		trainingService.save(trainingCenter);
		logger.info("Training center saved successfully with ID: {}", trainingCenter.getId());
		return new ResponseEntity<>(trainingCenter, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteById")
	public ResponseEntity<String> deleteTrainingCenter(@RequestParam("id")  int id) {
		logger.info("Deleting training center with ID: {}", id);
		trainingService.deleteById(id);
		logger.info("Training center with ID {} deleted successfully", id);
		return new ResponseEntity<>("Training Center Deleted Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllTrainingCenters() {
		logger.info("Deleting all training centers...");
		trainingService.deleteAll();
		logger.info("All training centers deleted successfully");
		return new ResponseEntity<>("All training centers deleted successfully", HttpStatus.OK);
	}

}
