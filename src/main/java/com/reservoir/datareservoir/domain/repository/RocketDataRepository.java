package com.reservoir.datareservoir.domain.repository;

import com.reservoir.datareservoir.domain.model.RocketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketDataRepository extends JpaRepository<RocketData, Long>,
        JpaSpecificationExecutor<RocketData>, FilterRepositoryUtil {
}
