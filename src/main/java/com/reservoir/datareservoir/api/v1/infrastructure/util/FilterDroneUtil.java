package com.reservoir.datareservoir.api.v1.infrastructure.util;

import java.time.OffsetDateTime;

import com.reservoir.datareservoir.api.v1.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.api.v1.domain.repository.DroneDataRepository;

public class FilterDroneUtil {

	private static boolean from;
    private static boolean to;
	
	public static OffsetDateTime checkFilterAndGet(PropertiesFilter propertiesFilter, String grantedAuthority,
			DroneDataRepository droneDataRepository) {
		
		from = isFromTimeStampNull(propertiesFilter);
        to = isToTimeStampNull(propertiesFilter);
        if (grantedAuthority.equals("ADMIN") && propertiesFilter.getUser() != null) {
            return getLastModifiedDataWithUser(propertiesFilter, propertiesFilter.getUser(), droneDataRepository);
        } else if (grantedAuthority.equals("ADMIN")) {
            if (!from && !to)
                return droneDataRepository.findMaxDateByTimeStampBaseBetween(propertiesFilter.getFromTimeStamp(),
                        propertiesFilter.getToTimeStamp());
            else if (!from)
                return droneDataRepository.findMaxDateByTimeStampBaseIsGreaterThanEqual(propertiesFilter.getFromTimeStamp());
            else if (!to)
                return droneDataRepository.findMaxDateByTimeStampBaseIsLessThanEqual(propertiesFilter.getToTimeStamp());
            else
                return droneDataRepository.findMaxDate();
        } else
            return getLastModifiedDataWithUser(propertiesFilter, grantedAuthority, droneDataRepository);
	}
	
	private static OffsetDateTime getLastModifiedDataWithUser(PropertiesFilter propertiesFilter, String grantedAuthority,
			DroneDataRepository droneDataRepository) {
		if (!from && !to)
            return droneDataRepository.findMaxDateByTimeStampBaseBetweenAndUser(propertiesFilter.getFromTimeStamp(),
                    propertiesFilter.getToTimeStamp(), grantedAuthority);
        else if (!from)
            return droneDataRepository.findMaxDateByTimeStampBaseIsGreaterThanEqualAndUser(propertiesFilter.getFromTimeStamp(),
                    grantedAuthority);
        else if (!to)
            return droneDataRepository.findMaxDateByTimeStampBaseIsLessThanEqualAndUser(propertiesFilter.getToTimeStamp(),
                    grantedAuthority);
        else
            return droneDataRepository.findMaxDateByUser(grantedAuthority);
	}
	
	private static boolean isFromTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getFromTimeStamp() == null;
    }

    private static boolean isToTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getToTimeStamp() == null;
    }

}
