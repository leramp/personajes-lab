package com.alkemy.personajes.personajes.repository.specification;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {
    public Specification<PersonajeEntity>
    getByFilters(PersonajeFiltersDTO filtersDTO){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if(StringUtils.hasLength((filtersDTO.getAge()))){
                int age = Integer.parseInt(filtersDTO.getAge());
                predicates.add(
                        criteriaBuilder.equal(root.get("edad"), age)
                );
            }
            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<PeliculaEntity, PersonajeEntity> join = root.join("peliculas", JoinType.INNER);
                Expression<String> moviesId =  join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            query.distinct(true);
            String orderByFiled = "nombre";
            query.orderBy(
                    filtersDTO.isASC()?
                            criteriaBuilder.asc(root.get(orderByFiled)):
                            criteriaBuilder.desc(root.get(orderByFiled))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
