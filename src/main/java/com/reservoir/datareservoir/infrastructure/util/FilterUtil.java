package com.reservoir.datareservoir.infrastructure.util;

import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.domain.modelinterface.Entities;
import com.reservoir.datareservoir.domain.repository.FilterRepositoryUtil;

import java.util.List;

public class FilterUtil {

    private static boolean from;
    private static boolean to;

    public static void checkTimeStampAndDelete(PropertiesFilter propertiesFilter, FilterRepositoryUtil repository) {
        from = isFromTimeStampNull(propertiesFilter);
        to = isToTimeStampNull(propertiesFilter);
        if (!from && !to)
            repository.deleteByTimeStampBaseBetween(propertiesFilter.getFromTimeStamp(),
                    propertiesFilter.getToTimeStamp());
        else if (!from)
            repository.deleteByTimeStampBaseIsGreaterThanEqual(propertiesFilter.getFromTimeStamp());
        else if (!to)
            repository.deleteByTimeStampBaseIsLessThanEqual(propertiesFilter.getToTimeStamp());
        else
            repository.deleteAll();
    }

    public static void checkFilterAndDelete(PropertiesFilter propertiesFilter, String grantedAuthority, FilterRepositoryUtil repository) {
        from = isFromTimeStampNull(propertiesFilter);
        to = isToTimeStampNull(propertiesFilter);
        if (propertiesFilter.getUser() != null) {
            deleteDataListWithUser(propertiesFilter, propertiesFilter.getUser(), repository);
        } else {
            if (!from && !to)
                repository.deleteByTimeStampBaseBetween(propertiesFilter.getFromTimeStamp(),
                        propertiesFilter.getToTimeStamp());
            else if (!from)
                repository.deleteByTimeStampBaseIsGreaterThanEqual(propertiesFilter.getFromTimeStamp());
            else if (!to)
                repository.deleteByTimeStampBaseIsLessThanEqual(propertiesFilter.getToTimeStamp());
            else
                repository.deleteAll();
        }
    }

    public static List<? extends Entities> checkFilterAndGet(PropertiesFilter propertiesFilter, String grantedAuthority,
                                                             FilterRepositoryUtil repository) {
        from = isFromTimeStampNull(propertiesFilter);
        to = isToTimeStampNull(propertiesFilter);
        if (grantedAuthority.equals("ADMIN") && propertiesFilter.getUser() != null) {
            return getDataListWithUser(propertiesFilter, propertiesFilter.getUser(), repository);
        } else if (grantedAuthority.equals("ADMIN")) {
            if (!from && !to)
                return repository.findByTimeStampBaseBetween(propertiesFilter.getFromTimeStamp(),
                        propertiesFilter.getToTimeStamp());
            else if (!from)
                return repository.findByTimeStampBaseIsGreaterThanEqual(propertiesFilter.getFromTimeStamp());
            else if (!to)
                return repository.findByTimeStampBaseIsLessThanEqual(propertiesFilter.getToTimeStamp());
            else
                return repository.findAll();
        } else
            return getDataListWithUser(propertiesFilter, grantedAuthority, repository);
    }

    private static List<? extends Entities> getDataListWithUser(PropertiesFilter propertiesFilter, String grantedAuthority,
                                                                FilterRepositoryUtil repository) {
        if (!from && !to)
            return repository.findByTimeStampBaseBetweenAndUser(propertiesFilter.getFromTimeStamp(),
                    propertiesFilter.getToTimeStamp(), grantedAuthority);
        else if (!from)
            return repository.findByTimeStampBaseIsGreaterThanEqualAndUser(propertiesFilter.getFromTimeStamp(),
                    grantedAuthority);
        else if (!to)
            return repository.findByTimeStampBaseIsLessThanEqualAndUser(propertiesFilter.getToTimeStamp(),
                    grantedAuthority);
        else
            return repository.findByUser(grantedAuthority);
    }

    private static void deleteDataListWithUser(PropertiesFilter propertiesFilter, String grantedAuthority, FilterRepositoryUtil repository) {
        if (!from && !to)
            repository.deleteByTimeStampBaseBetweenAndUser(propertiesFilter.getFromTimeStamp(),
                    propertiesFilter.getToTimeStamp(), grantedAuthority);
        else if (!from)
            repository.deleteByTimeStampBaseIsGreaterThanEqualAndUser(propertiesFilter.getFromTimeStamp(),
                    grantedAuthority);
        else if (!to)
            repository.deleteByTimeStampBaseIsLessThanEqualAndUser(propertiesFilter.getToTimeStamp(),
                    grantedAuthority);
        else
            repository.deleteByUser(grantedAuthority);
    }

    private static boolean isFromTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getFromTimeStamp() == null;
    }

    private static boolean isToTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getToTimeStamp() == null;
    }


}
