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

	/**
		Endpoint to get all Training Center
	*/
	@GetMapping("/list")
	public ResponseEntity<List<TrainingCenter>> listTrainingCenters() {
		logger.info("Fetching all training centers...");
		List<TrainingCenter> trainingCenters = trainingService.findAll();
		return new ResponseEntity<>(trainingCenters, HttpStatus.OK);
	}
	/**
	   Endpoint to Fetch using id
	 */
	@GetMapping("/findById")
	public ResponseEntity<TrainingCenter> findCenterById(@RequestParam("id")  int id) {
		logger.info("Fetching training center with ID: {}", id);
		TrainingCenter trainingCenter = trainingService.findById(id);
		if (trainingCenter == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(trainingCenter, HttpStatus.OK);
	}
	/**
	 Endpoint to create new entry
	 */
	@PostMapping("/save")
	public ResponseEntity<TrainingCenter> addTrainingCenterRequest(@Valid @RequestBody TrainingCenter trainingCenter) {
		logger.info("Saving new training center: {}", trainingCenter);
		trainingService.save(trainingCenter);
		return new ResponseEntity<>(trainingCenter, HttpStatus.CREATED);
	}
	/**
	 Endpoint to delete using id
	 */
	@DeleteMapping("/deleteById")
	public ResponseEntity<String> deleteTrainingCenter(@RequestParam("id")  int id) {
		logger.info("Deleting training center with ID: {}", id);
		trainingService.deleteById(id);
		return new ResponseEntity<>("Training Center Deleted Successfully", HttpStatus.OK);
	}
	/**
	 Endpoint Delete all centers
	 */
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllTrainingCenters() {
		logger.info("Deleting all training centers...");
		trainingService.deleteAll();
		return new ResponseEntity<>("All training centers deleted successfully", HttpStatus.OK);
	}

}
