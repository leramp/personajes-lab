package com.alkemy.personajes.personajes.repository.specification;

import com.alkemy.personajes.personajes.dto.PeliculaFiltersDTO;
import com.alkemy.personajes.personajes.dto.PersonajeFiltersDTO;
import com.alkemy.personajes.personajes.entity.PeliculaEntity;
import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSpecification {
    public Specification<PeliculaEntity>
    getByFilters(PeliculaFiltersDTO filtersDTO){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getName())){

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if(StringUtils.hasLength((filtersDTO.getGenre()))){

                Long genre = Long.parseLong(filtersDTO.getGenre());

                predicates.add(
                        criteriaBuilder.equal(root.get("generoId"), genre)
                );
            }
            query.distinct(true);
            String orderByFiled = "fechaCreacion";
            query.orderBy(
                    filtersDTO.isASC()?
                            criteriaBuilder.asc(root.get(orderByFiled)):
                            criteriaBuilder.desc(root.get(orderByFiled))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
