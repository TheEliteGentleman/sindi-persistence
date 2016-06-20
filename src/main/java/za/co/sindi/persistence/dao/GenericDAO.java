/**
 * 
 */
package za.co.sindi.persistence.dao;

import java.io.Serializable;
import java.util.Collection;

import za.co.sindi.persistence.SortOrder;
import za.co.sindi.persistence.entity.Entity;
import za.co.sindi.persistence.exception.DAOException;
import za.co.sindi.persistence.pagination.Pagination;

/**
 * @author Bienfait Sindi
 * @since 07 July 2010
 *
 */
public interface GenericDAO<T extends Entity<PK>, PK extends Serializable> {

	public long countAll() throws DAOException;
	public boolean delete(final T entity) throws DAOException;
	public boolean delete(final PK id) throws DAOException;
	public boolean deleteAll() throws DAOException;
	public boolean exists(final PK id) throws DAOException;
	public Collection<T> findAll() throws DAOException;
	public Collection<T> findAll(Pagination paging) throws DAOException;
	public Collection<T> findAll(SortOrder... sortOrders) throws DAOException;
	public Collection<T> findAll(Pagination paging, SortOrder... sortOrders) throws DAOException;
	public T find(final PK id) throws DAOException;
	public boolean save(final T entity) throws DAOException;
	public boolean saveInBatch(final T[] entities) throws DAOException;
	public boolean update(final T entity) throws DAOException;
	public boolean updateInBatch(final T[] entities) throws DAOException;
}
