package com.reservoir.datareservoir.domain.service;

import com.reservoir.datareservoir.core.security.ReservoirSecurity;
import com.reservoir.datareservoir.domain.exception.CubeNotFoundException;
import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.domain.model.CubeData;
import com.reservoir.datareservoir.domain.repository.CubeDataRepository;
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
public class CubeDataService {

    private final CubeDataRepository cubeDataRepository;

    public List<CubeData> getAll(PropertiesFilter propertiesFilter, Collection<? extends GrantedAuthority> authorities) {
        List<CubeData> cubeDataList = new ArrayList<>();
        authorities.forEach(grantedAuthority -> {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                cubeDataList.addAll((List<CubeData>) FilterUtil.checkFilterAndGet(propertiesFilter,
                        grantedAuthority.getAuthority(), cubeDataRepository));
            }
        });
        return cubeDataList;
    }

    public CubeData getOne(Long cubeId, Collection<? extends GrantedAuthority> authorities) throws AccessDeniedException {
        CubeData cubeData = null;
        for (GrantedAuthority grantedAuthority : authorities) {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                cubeData = getOrFail(cubeId, grantedAuthority.getAuthority());
            }
        }
        return cubeData;
    }

    @Transactional
    public void save(CubeData cubeData) {
        cubeDataRepository.save(cubeData);
    }

    @Transactional
    public void deleteMulti(PropertiesFilter propertiesFilter, Collection<? extends GrantedAuthority> authorities) {
        authorities.forEach(grantedAuthority -> {
            if (ReservoirSecurity.isGrantAuthorityValid(grantedAuthority.getAuthority())) {
                FilterUtil.checkFilterAndDelete(propertiesFilter, grantedAuthority.getAuthority(), cubeDataRepository);
            }
        });
    }

    @Transactional
    public void deleteOne(Long cubeId) {
        try {
            cubeDataRepository.deleteById(cubeId);
            cubeDataRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CubeNotFoundException(cubeId);
        }
    }

    private CubeData getOrFail(Long cubeId, String user) throws AccessDeniedException {
        CubeData cubeData = cubeDataRepository.findById(cubeId)
                .orElseThrow(() -> new CubeNotFoundException(cubeId));
        if (!cubeData.getUser().equals(user) && !user.equals("ADMIN"))
            throw new AccessDeniedException("Your user cannot access this data. It doesn't belong to your user.");
        return cubeData;
    }

}
