/**
 * 
 */
package za.co.sindi.persistence.dao;

import java.util.Collection;

import za.co.sindi.persistence.SortOrder;
import za.co.sindi.persistence.domain.Specification;
import za.co.sindi.persistence.exception.DAOException;
import za.co.sindi.persistence.pagination.Pagination;

/**
 * @author buhake.sindi
 * @since 06 March 2018
 *
 */
public interface SpecificationDAO<T, S extends Specification<T>> {
	
	public long count(S specification);
	public T find(S specification);
	public Collection<T> findAll(S specification) throws DAOException;
	public Collection<T> findAll(S specification, SortOrder... sortOrders) throws DAOException;
	public Collection<T> findAll(S specification, Pagination paging) throws DAOException;
	public Collection<T> findAll(S specification, Pagination paging, SortOrder... sortOrders) throws DAOException;

}
