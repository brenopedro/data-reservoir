package com.reservoir.datareservoir.domain.service;

import com.reservoir.datareservoir.core.security.ReservoirSecurity;
import com.reservoir.datareservoir.domain.exception.RocketNotFoundException;
import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.domain.model.RocketData;
import com.reservoir.datareservoir.domain.repository.RocketDataRepository;
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
public class RocketDataService {

    private final RocketDataRepository rocketDataRepository;

    public List<RocketData> getAll(PropertiesFilter propertiesFilter, Collection<? extends GrantedAuthority> authorities) {
        List<RocketData> rocketDataList = new ArrayList<>();
        authorities.forEach(grantedAuthority -> {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                rocketDataList.addAll((List<RocketData>) FilterUtil.checkFilterAndGet(propertiesFilter,
                        grantedAuthority.getAuthority(), rocketDataRepository));
            }
        });
        return rocketDataList;
    }

    public RocketData getOne(Long rocketId, Collection<? extends GrantedAuthority> authorities) throws AccessDeniedException {
        RocketData rocketData = null;
        for (GrantedAuthority grantedAuthority: authorities) {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                rocketData = getOrFail(rocketId, grantedAuthority.getAuthority());
            }
        }
        return rocketData;
    }

    @Transactional
    public void save(RocketData rocketData) {
        rocketDataRepository.save(rocketData);
    }

    @Transactional
    public void deleteMulti(PropertiesFilter propertiesFilter, Collection<? extends GrantedAuthority> authorities) {
        authorities.forEach(grantedAuthority -> {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                FilterUtil.checkFilterAndDelete(propertiesFilter, grantedAuthority.getAuthority(), rocketDataRepository);
            }
        });
    }

    @Transactional
    public void deleteOne(Long rocketId) {
        try {
            rocketDataRepository.deleteById(rocketId);
            rocketDataRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RocketNotFoundException(rocketId);
        }
    }

    private RocketData getOrFail(Long rocketId, String user) {
        RocketData rocketData = rocketDataRepository.findById(rocketId)
                .orElseThrow(() -> new RocketNotFoundException(rocketId));
        if (!rocketData.getUser().equals(user) && !user.equals("ADMIN"))
            throw new AccessDeniedException("Your user cannot access this data. It doesn't belong to your user.");
        return rocketData;
    }
}
