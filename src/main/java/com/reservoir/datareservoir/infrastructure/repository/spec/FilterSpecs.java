package com.reservoir.datareservoir.infrastructure.repository.spec;

import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class FilterSpecs {

    public static Specification<?> filter(PropertiesFilter propertiesFilter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (propertiesFilter.getFromTimeStamp() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("timeStampBase"),
                        propertiesFilter.getFromTimeStamp()));
            }

            if (propertiesFilter.getToTimeStamp() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timeStampBase"),
                        propertiesFilter.getToTimeStamp()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
