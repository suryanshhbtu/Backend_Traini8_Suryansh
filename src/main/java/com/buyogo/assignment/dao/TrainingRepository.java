package com.buyogo.assignment.dao;

import com.buyogo.assignment.entity.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<TrainingCenter, Integer> {

    // that's it ... no need to write any code

}
