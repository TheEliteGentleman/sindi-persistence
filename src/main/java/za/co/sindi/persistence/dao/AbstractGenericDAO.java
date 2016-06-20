/**
 * 
 */
package za.co.sindi.persistence.dao;

import java.io.Serializable;
import java.util.logging.Logger;

import za.co.sindi.persistence.entity.Entity;

/**
 * @author Bienfait Sindi
 * @since 07 July 2010
 * 
 */
public abstract class AbstractGenericDAO<T extends Entity<PK>, PK extends Serializable> implements GenericDAO<T, PK> {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	

//	/* (non-Javadoc)
//	 * @see com.neurologic.music4point0.dao.GenericDAO#findAll()
//	 */
//	public List<T> findAll() throws DAOException {
//		// TODO Auto-generated method stub
//		return findByRange(0, 0);
//	}
//
//	@SuppressWarnings("unchecked")
//	public abstract boolean[] delete(PK... ids) throws DAOException;
//	public abstract List<T> findByRange(final int start, final int range) throws DAOException;
}
