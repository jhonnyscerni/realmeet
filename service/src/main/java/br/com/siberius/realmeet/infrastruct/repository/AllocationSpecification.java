package br.com.siberius.realmeet.infrastruct.repository;

import br.com.siberius.realmeet.api.model.filter.AllocationFilter;
import br.com.siberius.realmeet.domain.entity.Allocation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class AllocationSpecification implements Specification<Allocation> {

    private AllocationFilter allocationFilter;

    @Override
    public Predicate toPredicate(Root<Allocation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(allocationFilter.getEmployeeName())
            .ifPresent(p -> predicates.add(criteriaBuilder.like(root.get("employee").get("name"), "%" + allocationFilter.getEmployeeName() + "%")));
        Optional.ofNullable(allocationFilter.getEmployeeEmail())
            .ifPresent(p -> predicates.add(criteriaBuilder.like(root.get("employee").get("email"), "%" + allocationFilter.getEmployeeEmail() + "%")));
        Optional.ofNullable(allocationFilter.getSubject())
            .ifPresent(p -> predicates.add(criteriaBuilder.like(root.get("subject"), "%" + allocationFilter.getSubject() + "%")));
        Optional.ofNullable(allocationFilter.getStartAt())
            .ifPresent(p -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startAt"), p)));
        Optional.ofNullable(allocationFilter.getEndAt()).ifPresent(p -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endAt"), p)));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
