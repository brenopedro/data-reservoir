package com.reservoir.datareservoir.domain.service;

import com.reservoir.datareservoir.core.security.ReservoirSecurity;
import com.reservoir.datareservoir.domain.exception.DroneNotFoundException;
import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.domain.model.DroneData;
import com.reservoir.datareservoir.domain.repository.DroneDataRepository;
import com.reservoir.datareservoir.infrastructure.util.FilterUtil;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class DroneDataService {

    private final DroneDataRepository droneDataRepository;

    public List<DroneData> getAll(PropertiesFilter propertiesFilter, Collection<? extends GrantedAuthority> authorities) {
        List<DroneData> droneDataList = new ArrayList<>();
        authorities.forEach(grantedAuthority -> {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                droneDataList.addAll((List<DroneData>) FilterUtil.checkFilterAndGet(propertiesFilter,
                        grantedAuthority.getAuthority(), droneDataRepository));
            }
        });
        return droneDataList;
    }

    public DroneData getOne(Long droneId, Collection<? extends GrantedAuthority> authorities) throws AccessDeniedException {
        DroneData droneData = null;
        for (GrantedAuthority grantedAuthority: authorities) {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                droneData = getOrFail(droneId, grantedAuthority.getAuthority());
            }
        }
        return droneData;
    }

    @Transactional
    public void save(DroneData droneData) {
        droneDataRepository.save(droneData);
    }

    @Transactional
    public void deleteMulti(PropertiesFilter propertiesFilter, Collection<? extends GrantedAuthority> authorities) {
        authorities.forEach(grantedAuthority -> {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                FilterUtil.checkFilterAndDelete(propertiesFilter, grantedAuthority.getAuthority(), droneDataRepository);
            }
        });
    }

    @Transactional
    public void deleteOne(Long droneId) {
        try {
            droneDataRepository.deleteById(droneId);
            droneDataRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new DroneNotFoundException(droneId);
        }
    }

    private DroneData getOrFail(Long droneId, String user) {
        DroneData droneData = droneDataRepository.findById(droneId)
                .orElseThrow(() -> new DroneNotFoundException(droneId));
        if (!droneData.getUser().equals(user) && !user.equals("ADMIN"))
            throw new AccessDeniedException("Your user cannot access this data. It doesn't belong to your user.");
        return droneData;
    }
}
