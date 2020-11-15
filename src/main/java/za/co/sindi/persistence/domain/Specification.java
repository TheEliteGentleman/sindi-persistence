/**
 * 
 */
package za.co.sindi.persistence.domain;

/**
 * @author buhake.sindi
 * @since 06 March 2018
 *
 */
public interface Specification<T> {

	public boolean isSatisfiedBy(T t);
}
