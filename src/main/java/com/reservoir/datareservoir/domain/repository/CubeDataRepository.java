package com.reservoir.datareservoir.domain.repository;

import com.reservoir.datareservoir.domain.model.CubeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CubeDataRepository extends JpaRepository<CubeData, Long>,
        JpaSpecificationExecutor<CubeData>, FilterRepositoryUtil {
}
