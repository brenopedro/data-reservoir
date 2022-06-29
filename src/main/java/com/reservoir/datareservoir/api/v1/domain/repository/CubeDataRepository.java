package com.reservoir.datareservoir.api.v1.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reservoir.datareservoir.api.v1.domain.model.CubeData;

@Repository
public interface CubeDataRepository extends JpaRepository<CubeData, Long>,
        JpaSpecificationExecutor<CubeData>, FilterRepositoryUtil {

	@Query("select max(timeStampBase) from CubeData where :fromTimeStamp <= timeStampBase and timeStampBase <= :toTimeStamp")
	OffsetDateTime findMaxDateByTimeStampBaseBetween(OffsetDateTime fromTimeStamp, OffsetDateTime toTimeStamp);
	
	@Query("select max(timeStampBase) from CubeData where :fromTimeStamp <= timeStampBase")
	OffsetDateTime findMaxDateByTimeStampBaseIsGreaterThanEqual(OffsetDateTime fromTimeStamp);
	
	@Query("select max(timeStampBase) from CubeData where timeStampBase <= :toTimeStamp")
	OffsetDateTime findMaxDateByTimeStampBaseIsLessThanEqual(OffsetDateTime toTimeStamp);
	
	@Query("select max(timeStampBase) from CubeData")
	OffsetDateTime findMaxDate();
	
	@Query("select max(timeStampBase) from CubeData where :fromTimeStamp <= timeStampBase and timeStampBase <= :toTimeStamp and user = :user")
	OffsetDateTime findMaxDateByTimeStampBaseBetweenAndUser(OffsetDateTime fromTimeStamp, OffsetDateTime toTimeStamp, String user);
	
	@Query("select max(timeStampBase) from CubeData where :fromTimeStamp <= timeStampBase and user = :user")
	OffsetDateTime findMaxDateByTimeStampBaseIsGreaterThanEqualAndUser(OffsetDateTime fromTimeStamp, String user);
	
	@Query("select max(timeStampBase) from CubeData where timeStampBase <= :toTimeStamp and user = :user")
	OffsetDateTime findMaxDateByTimeStampBaseIsLessThanEqualAndUser(OffsetDateTime toTimeStamp, String user);
	
	@Query("select max(timeStampBase) from CubeData where user = :user")
	OffsetDateTime findMaxDateByUser(String user);
}
