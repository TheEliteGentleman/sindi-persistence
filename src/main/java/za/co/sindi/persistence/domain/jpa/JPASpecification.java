/**
 * 
 */
package za.co.sindi.persistence.domain.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import za.co.sindi.persistence.domain.Specification;

/**
 * @author buhake.sindi
 *@since 06 March 2018
 *
 */
public interface JPASpecification<T> extends Specification<T> {

	public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);
}
