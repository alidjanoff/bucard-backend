package com.example.bucard.filter;

import com.example.bucard.dao.entity.ProfileEntity;
import com.example.bucard.dao.entity.UserEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class FilterSpecification<T> {
    public Specification<T> getSpecification(String filter) {
        return (root, query, criteriaBuilder) ->
        {
            Join<ProfileEntity, UserEntity> users  = root.join("user", JoinType.INNER);
            Predicate fullName = criteriaBuilder.like(users.get("fullName"), "%" + filter + "%");
            Predicate profession = criteriaBuilder.like(root.get("profession"), "%" + filter + "%");
            Predicate location = criteriaBuilder.like(root.get("location"), "%" + filter + "%");
            return criteriaBuilder.or(fullName,profession,location);
        };

    }
}
