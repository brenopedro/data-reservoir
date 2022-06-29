package com.reservoir.datareservoir.api.v1.infrastructure.util;

import java.time.OffsetDateTime;

import com.reservoir.datareservoir.api.v1.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.api.v1.domain.repository.CubeDataRepository;

public class FilterCubeUtil {

	private static boolean from;
    private static boolean to;
	
	public static OffsetDateTime checkFilterAndGet(PropertiesFilter propertiesFilter, String grantedAuthority,
			CubeDataRepository cubeDataRepository) {
		
		from = isFromTimeStampNull(propertiesFilter);
        to = isToTimeStampNull(propertiesFilter);
        if (grantedAuthority.equals("ADMIN") && propertiesFilter.getUser() != null) {
            return getLastModifiedDataWithUser(propertiesFilter, propertiesFilter.getUser(), cubeDataRepository);
        } else if (grantedAuthority.equals("ADMIN")) {
            if (!from && !to)
                return cubeDataRepository.findMaxDateByTimeStampBaseBetween(propertiesFilter.getFromTimeStamp(),
                        propertiesFilter.getToTimeStamp());
            else if (!from)
                return cubeDataRepository.findMaxDateByTimeStampBaseIsGreaterThanEqual(propertiesFilter.getFromTimeStamp());
            else if (!to)
                return cubeDataRepository.findMaxDateByTimeStampBaseIsLessThanEqual(propertiesFilter.getToTimeStamp());
            else
                return cubeDataRepository.findMaxDate();
        } else
            return getLastModifiedDataWithUser(propertiesFilter, grantedAuthority, cubeDataRepository);
	}
	
	private static OffsetDateTime getLastModifiedDataWithUser(PropertiesFilter propertiesFilter, String grantedAuthority,
			CubeDataRepository cubeDataRepository) {
		if (!from && !to)
            return cubeDataRepository.findMaxDateByTimeStampBaseBetweenAndUser(propertiesFilter.getFromTimeStamp(),
                    propertiesFilter.getToTimeStamp(), grantedAuthority);
        else if (!from)
            return cubeDataRepository.findMaxDateByTimeStampBaseIsGreaterThanEqualAndUser(propertiesFilter.getFromTimeStamp(),
                    grantedAuthority);
        else if (!to)
            return cubeDataRepository.findMaxDateByTimeStampBaseIsLessThanEqualAndUser(propertiesFilter.getToTimeStamp(),
                    grantedAuthority);
        else
            return cubeDataRepository.findMaxDateByUser(grantedAuthority);
	}
	
	private static boolean isFromTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getFromTimeStamp() == null;
    }

    private static boolean isToTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getToTimeStamp() == null;
    }

}
