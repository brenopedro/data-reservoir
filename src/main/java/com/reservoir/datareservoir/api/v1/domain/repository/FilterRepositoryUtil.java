package com.reservoir.datareservoir.api.v1.domain.repository;

import java.time.OffsetDateTime;
import java.util.List;

import com.reservoir.datareservoir.api.v1.domain.modelinterface.Entities;

public interface FilterRepositoryUtil {

    List<? extends Entities> findByTimeStampBaseIsLessThanEqual(OffsetDateTime toTimeStamp);
    List<? extends Entities> findByTimeStampBaseIsGreaterThanEqual(OffsetDateTime fromTimeStamp);
    List<? extends Entities> findByTimeStampBaseBetween(OffsetDateTime fromTimeStamp, OffsetDateTime toTimeStamp);
    List<? extends Entities> findAll();

    List<? extends Entities> findByTimeStampBaseIsLessThanEqualAndUser(OffsetDateTime toTimeStamp, String user);
    List<? extends Entities> findByTimeStampBaseIsGreaterThanEqualAndUser(OffsetDateTime fromTimeStamp, String user);
    List<? extends Entities> findByTimeStampBaseBetweenAndUser(OffsetDateTime fromTimeStamp, OffsetDateTime toTimeStamp, String user);
    List<? extends Entities> findByUser(String user);

    void deleteByTimeStampBaseIsLessThanEqual(OffsetDateTime toTimeStamp);
    void deleteByTimeStampBaseIsGreaterThanEqual(OffsetDateTime fromTimeStamp);
    void deleteByTimeStampBaseBetween(OffsetDateTime fromTimeStamp, OffsetDateTime toTimeStamp);
    void deleteAll();

    void deleteByTimeStampBaseIsLessThanEqualAndUser(OffsetDateTime toTimeStamp, String user);
    void deleteByTimeStampBaseIsGreaterThanEqualAndUser(OffsetDateTime fromTimeStamp, String user);
    void deleteByTimeStampBaseBetweenAndUser(OffsetDateTime fromTimeStamp, OffsetDateTime toTimeStamp, String user);
    void deleteByUser(String user);
}
