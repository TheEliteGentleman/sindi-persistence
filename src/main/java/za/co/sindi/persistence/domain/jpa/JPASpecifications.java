/**
 * 
 */
package za.co.sindi.persistence.domain.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author buhake.sindi
 * @since 06 March 2018
 *
 */
public class JPASpecifications<T> implements JPASpecification<T> {

	private final JPASpecification<T> specification;
	
	/**
	 * @param type
	 */
	private JPASpecifications(final JPASpecification<T> specification) {
		super();
		this.specification = specification;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.domain.Specification#isSatisfiedBy(java.lang.Object)
	 */
	@Override
	public boolean isSatisfiedBy(T t) {
		// TODO Auto-generated method stub
		return specification.isSatisfiedBy(t);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.domain.jpa.JPASpecification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaBuilder)
	 */
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return specification.toPredicate(root, criteriaBuilder);
	}
	
	public static <T> JPASpecification<T> where(JPASpecification<T> specification) {
		return new JPASpecifications<>(specification);
	}
	
	public JPASpecification<T> and(final JPASpecification<T> other) {
		return new JPASpecifications<>(new JPASpecification<T>() {

			/* (non-Javadoc)
			 * @see za.co.sindi.persistence.domain.Specification#isSatisfiedBy(java.lang.Object)
			 */
			@Override
			public boolean isSatisfiedBy(T t) {
				// TODO Auto-generated method stub
				return specification.isSatisfiedBy(t) && other.isSatisfiedBy(t);
			}

			/* (non-Javadoc)
			 * @see za.co.sindi.persistence.domain.jpa.JPASpecification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaBuilder)
			 */
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.and(specification.toPredicate(root, criteriaBuilder), other.toPredicate(root, criteriaBuilder));
			}
		});
	}
	
	public JPASpecification<T> or(final JPASpecification<T> other) {
		return new JPASpecifications<>(new JPASpecification<T>() {

			/* (non-Javadoc)
			 * @see za.co.sindi.persistence.domain.Specification#isSatisfiedBy(java.lang.Object)
			 */
			@Override
			public boolean isSatisfiedBy(T t) {
				// TODO Auto-generated method stub
				return specification.isSatisfiedBy(t) || other.isSatisfiedBy(t);
			}

			/* (non-Javadoc)
			 * @see za.co.sindi.persistence.domain.jpa.JPASpecification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaBuilder)
			 */
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.or(specification.toPredicate(root, criteriaBuilder), other.toPredicate(root, criteriaBuilder));
			}
		});
	}
	
	public JPASpecification<T> not() {
		return new JPASpecifications<>(new JPASpecification<T>() {

			/* (non-Javadoc)
			 * @see za.co.sindi.persistence.domain.Specification#isSatisfiedBy(java.lang.Object)
			 */
			@Override
			public boolean isSatisfiedBy(T t) {
				// TODO Auto-generated method stub
				return !specification.isSatisfiedBy(t);
			}

			/* (non-Javadoc)
			 * @see za.co.sindi.persistence.domain.jpa.JPASpecification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaBuilder)
			 */
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.not(specification.toPredicate(root, criteriaBuilder));
			}
		});
	}
}
