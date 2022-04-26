package com.reservoir.datareservoir.api.v1.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.reservoir.datareservoir.api.v1.domain.model.DroneData;

@Repository
public interface DroneDataRepository extends JpaRepository<DroneData, Long>,
        JpaSpecificationExecutor<DroneData>, FilterRepositoryUtil {
}
