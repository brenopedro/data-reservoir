package com.reservoir.datareservoir.api.v1.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.reservoir.datareservoir.api.v1.domain.model.CubeData;

@Repository
public interface CubeDataRepository extends JpaRepository<CubeData, Long>,
        JpaSpecificationExecutor<CubeData>, FilterRepositoryUtil {
}
