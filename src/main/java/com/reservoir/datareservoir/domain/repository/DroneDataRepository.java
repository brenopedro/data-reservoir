package com.reservoir.datareservoir.domain.repository;

import com.reservoir.datareservoir.domain.model.DroneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneDataRepository extends JpaRepository<DroneData, Long>,
        JpaSpecificationExecutor<DroneData>, FilterRepositoryUtil {
}
