package com.reservoir.datareservoir.api.v1.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.reservoir.datareservoir.api.v1.domain.model.RocketData;

@Repository
public interface RocketDataRepository extends JpaRepository<RocketData, Long>,
        JpaSpecificationExecutor<RocketData>, FilterRepositoryUtil {
}
