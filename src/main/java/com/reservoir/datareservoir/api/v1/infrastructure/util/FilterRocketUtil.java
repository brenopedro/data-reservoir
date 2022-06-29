package com.reservoir.datareservoir.api.v1.infrastructure.util;

import java.time.OffsetDateTime;

import com.reservoir.datareservoir.api.v1.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.api.v1.domain.repository.RocketDataRepository;

public class FilterRocketUtil {

	private static boolean from;
    private static boolean to;
	
	public static OffsetDateTime checkFilterAndGet(PropertiesFilter propertiesFilter, String grantedAuthority,
			RocketDataRepository rocketDataRepository) {
		
		from = isFromTimeStampNull(propertiesFilter);
        to = isToTimeStampNull(propertiesFilter);
        if (grantedAuthority.equals("ADMIN") && propertiesFilter.getUser() != null) {
            return getLastModifiedDataWithUser(propertiesFilter, propertiesFilter.getUser(), rocketDataRepository);
        } else if (grantedAuthority.equals("ADMIN")) {
            if (!from && !to)
                return rocketDataRepository.findMaxDateByTimeStampBaseBetween(propertiesFilter.getFromTimeStamp(),
                        propertiesFilter.getToTimeStamp());
            else if (!from)
                return rocketDataRepository.findMaxDateByTimeStampBaseIsGreaterThanEqual(propertiesFilter.getFromTimeStamp());
            else if (!to)
                return rocketDataRepository.findMaxDateByTimeStampBaseIsLessThanEqual(propertiesFilter.getToTimeStamp());
            else
                return rocketDataRepository.findMaxDate();
        } else
            return getLastModifiedDataWithUser(propertiesFilter, grantedAuthority, rocketDataRepository);
	}
	
	private static OffsetDateTime getLastModifiedDataWithUser(PropertiesFilter propertiesFilter, String grantedAuthority,
			RocketDataRepository rocketDataRepository) {
		if (!from && !to)
            return rocketDataRepository.findMaxDateByTimeStampBaseBetweenAndUser(propertiesFilter.getFromTimeStamp(),
                    propertiesFilter.getToTimeStamp(), grantedAuthority);
        else if (!from)
            return rocketDataRepository.findMaxDateByTimeStampBaseIsGreaterThanEqualAndUser(propertiesFilter.getFromTimeStamp(),
                    grantedAuthority);
        else if (!to)
            return rocketDataRepository.findMaxDateByTimeStampBaseIsLessThanEqualAndUser(propertiesFilter.getToTimeStamp(),
                    grantedAuthority);
        else
            return rocketDataRepository.findMaxDateByUser(grantedAuthority);
	}
	
	private static boolean isFromTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getFromTimeStamp() == null;
    }

    private static boolean isToTimeStampNull(PropertiesFilter propertiesFilter) {
        return propertiesFilter.getToTimeStamp() == null;
    }

}
