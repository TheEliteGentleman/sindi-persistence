/**
 * 
 */
package za.co.sindi.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import za.co.sindi.persistence.SortOrder;
import za.co.sindi.persistence.domain.jpa.JPASpecification;
import za.co.sindi.persistence.entity.Entity;
import za.co.sindi.persistence.exception.DAOException;
import za.co.sindi.persistence.pagination.Pagination;

/**
 * @author Bienfait Sindi
 * @since 07 July 2010
 *
 */
public abstract class JPAGenericDAO<T extends Entity<PK>, PK extends Serializable> extends AbstractGenericDAO<T, PK> implements SpecificationDAO<T, JPASpecification<T>> {

	private static final String NULL_ENTITYMANAGER = "No EntityManager provided.";
	
	protected Class<T> persistentClass;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected JPAGenericDAO() {
		super();
		// TODO Auto-generated constructor stub
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.persistentClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	/**
	 * @param persistentClass
	 */
	protected JPAGenericDAO(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.dao.GenericDAO#countAll()
	 */
	@Override
	public long countAll() throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		long count = 0;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> query = builder.createQuery(Long.class);
			query.select(builder.count(query.from(persistentClass)));
			count = entityManager.createQuery(query).getSingleResult();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			if (e instanceof NoResultException) {
				count = 0;
			} else {
				throw new DAOException(e);
			}
		}
		
		return count;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.music4point0.dao.GenericDAO#delete(com.neurologic.music4point0.entity.base.Entity)
	 */
	@Override
	public boolean delete(T entity) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		boolean deleted = false;
		try {
//			entityManager.remove(entity);
			entityManager.remove(entityManager.merge(entity));
			deleted = true;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			deleted = false;
			throw new DAOException(e);
		}
		
		return deleted;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(PK id) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		boolean deleted = false;
		try {
			T entity = entityManager.getReference(persistentClass, id);
			if (entity != null) {
				entityManager.remove(entity);
				deleted = true;
			}
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			deleted = false;
			if (!(e instanceof EntityNotFoundException)) {
				throw new DAOException(e);
			}
		}
		
		return deleted;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#deleteAll()
	 */
	@Override
	public boolean deleteAll() throws DAOException {
		// TODO Auto-generated method stub
		try {
			final EntityManager entityManager = getEntityManager();
			
			if (entityManager == null) {
				throw new DAOException(NULL_ENTITYMANAGER);
			}
			
			Query query = entityManager.createQuery("DELETE FROM " + persistentClass.getSimpleName() + " c");
			
			return query.executeUpdate() > 0;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#exists(java.io.Serializable)
	 */
	@Override
	public boolean exists(PK id) throws DAOException {
		// TODO Auto-generated method stub
		try {
			final EntityManager entityManager = getEntityManager();
			
			if (entityManager == null) {
				throw new DAOException(NULL_ENTITYMANAGER);
			}
			
//			TypedQuery<Boolean> query = entityManager.createQuery("SELECT CASE WHEN (COUNT(c) > 0) THEN true ELSE false END FROM " + persistentClass.getSimpleName() + " c WHERE c.id = ?1", Boolean.class);
//			query.setParameter(1, id);
//			
//			return query.getSingleResult();
			@SuppressWarnings("unchecked")
			TypedQuery<PK> query = (TypedQuery<PK>) getEntityManager().createQuery("SELECT c.id FROM " + persistentClass.getSimpleName() + " c WHERE c.id = ?1", id.getClass());
			query.setParameter(1, id);
			query.setMaxResults(1);
			return query.getResultList().size() == 1;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
//			if (e instanceof NoResultException) {//This should NEVER happen
//				return false;
//			} else {
				throw new DAOException(e);
//			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.dao.GenericDAO#find(java.io.Serializable)
	 */
	public T find(PK id) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		return entityManager.find(persistentClass, id);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll()
	 */
	@Override
	public List<T> findAll() throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		TypedQuery<T> query = entityManager.createQuery("SELECT c FROM " + persistentClass.getSimpleName() + " c", persistentClass);
		return query.getResultList();
	}
	
//	/* (non-Javadoc)
//	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll(za.co.sindi.persistence.pagination.Pagination, boolean)
//	 */
//	@Override
//	public Collection<T> findAll(Pagination paging, boolean sortAscending) throws DAOException {
//		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("Method not supported.");
//	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll(za.co.sindi.persistence.pagination.Pagination)
	 */
	@Override
	public Collection<T> findAll(Pagination paging) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		TypedQuery<T> query = entityManager.createQuery("SELECT c FROM " + persistentClass.getSimpleName() + " c", persistentClass);
		query.setFirstResult(paging.getStartPosition());
		query.setMaxResults(paging.getRange());
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll(za.co.sindi.persistence.SortOrder[])
	 */
	@Override
	public Collection<T> findAll(SortOrder... sortOrders) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		final String entityAlias = "c";
		StringBuilder sb = new StringBuilder();
		for (SortOrder sortOrder : sortOrders) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			
			sb.append(entityAlias + "." + sortOrder.getPropertyName());
			sb.append(" " + (sortOrder.isAscending() ? "ASC" : "DESC"));
		}
		
		TypedQuery<T> query = entityManager.createQuery("SELECT " + entityAlias + " FROM " + persistentClass.getSimpleName() + " " + entityAlias + " ORDER BY " + sb.toString(), persistentClass);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll(za.co.sindi.persistence.pagination.Pagination, za.co.sindi.persistence.SortOrder[])
	 */
	@Override
	public Collection<T> findAll(Pagination paging, SortOrder... sortOrders) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		final String entityAlias = "c";
		StringBuilder sb = new StringBuilder();
		for (SortOrder sortOrder : sortOrders) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			
			sb.append(entityAlias + "." + sortOrder.getPropertyName());
			sb.append(" " + (sortOrder.isAscending() ? "ASC" : "DESC"));
		}
		
		TypedQuery<T> query = entityManager.createQuery("SELECT " + entityAlias + " FROM " + persistentClass.getSimpleName() + " " + entityAlias + " ORDER BY " + sb.toString(), persistentClass);
		query.setFirstResult(paging.getStartPosition());
		query.setMaxResults(paging.getRange());
		return query.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.SpecificationDAO#count(za.co.sindi.persistence.domain.Specification)
	 */
	@Override
	public long count(JPASpecification<T> specification) {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class); //builder.createQuery(persistentClass);
		Root<T> root = query.from(persistentClass);
		query.where(specification.toPredicate(root, builder));
		
		return entityManager.createQuery(query.select(builder.count(root))).getSingleResult();	
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.SpecificationDAO#find(za.co.sindi.persistence.domain.Specification)
	 */
	@Override
	public T find(JPASpecification<T> specification) {
		// TODO Auto-generated method stub
		return createQueryBySpecification(specification, (SortOrder[])null).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.SpecificationDAO#findAll(za.co.sindi.persistence.domain.Specification)
	 */
	@Override
	public Collection<T> findAll(JPASpecification<T> specification) throws DAOException {
		// TODO Auto-generated method stub
		return createQueryBySpecification(specification, (SortOrder[])null).getResultList();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll(za.co.sindi.persistence.domain.Specification, za.co.sindi.persistence.SortOrder[])
	 */
	@Override
	public Collection<T> findAll(JPASpecification<T> specification, SortOrder... sortOrders) throws DAOException {
		// TODO Auto-generated method stub
		return createQueryBySpecification(specification, sortOrders).getResultList();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll(za.co.sindi.persistence.domain.Specification, za.co.sindi.persistence.pagination.Pagination)
	 */
	@Override
	public Collection<T> findAll(JPASpecification<T> specification, Pagination paging) throws DAOException {
		// TODO Auto-generated method stub

		TypedQuery<T> query = createQueryBySpecification(specification, (SortOrder[])null);
		if (paging != null) {
			query.setFirstResult(paging.getStartPosition()).setMaxResults(paging.getRange());
		}
		
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#findAll(za.co.sindi.persistence.domain.Specification, za.co.sindi.persistence.pagination.Pagination, za.co.sindi.persistence.SortOrder[])
	 */
	@Override
	public Collection<T> findAll(JPASpecification<T> specification, Pagination paging, SortOrder... sortOrders) throws DAOException {
		// TODO Auto-generated method stub
		TypedQuery<T> query = createQueryBySpecification(specification, sortOrders);
		if (paging != null) {
			query.setFirstResult(paging.getStartPosition()).setMaxResults(paging.getRange());
		}
		
		return query.getResultList();
	}

	public T findByReference(PK id) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		try {
			return entityManager.getReference(persistentClass, id);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
//			throw new DAOException(e);
			return null;
		}
	}
	
//	/* (non-Javadoc)
//	 * @see za.co.sindi.dao.AbstractGenericDAO#findByRange(int, int)
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> findByRange(int start, int range) throws DAOException {
//		// TODO Auto-generated method stub
//		final EntityManager entityManager = getEntityManager();
//	
//		if (entityManager == null) {
//			throw new DAOException(NULL_ENTITYMANAGER);
//		}
//		
//		Query query = entityManager.createQuery("SELECT c FROM " + persistentClass.getSimpleName() + " c", persistentClass);
//		query.setFirstResult(start);
//		if (range > 0) {
//			query.setMaxResults(range);
//		}
//		return query.getResultList();
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#insert(za.co.sindi.persistence.dao.GenericDAO.entity.Entity)
	 */
	public boolean save(T entity) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		if (entity == null) {
			throw new IllegalArgumentException("Cannot insert a null entity.");
		}
		
//		if (!entity.isNew()) {
//			throw new DAOException("Entity is a not a new entity (it contains a primary key).");
//		}
		
		boolean inserted = false;
		try {
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.clear();
			inserted = true;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			inserted = false;
			throw new DAOException(e);
		}
		
		return inserted;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#saveInBatch(za.co.sindi.persistence.entity.Entity[])
	 */
	@Override
	public boolean saveInBatch(T[] entities) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		if (entities == null) {
			throw new IllegalArgumentException("Cannot insert a null entities.");
		}
		
//		if (!entity.isNew()) {
//			throw new DAOException("Entity is a not a new entity (it contains a primary key).");
//		}
		
		boolean inserted = false;
		try {
			for (T entity : entities) {
				entityManager.persist(entity);
			}
			entityManager.flush();
			entityManager.clear();
			inserted = true;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			inserted = false;
			throw new DAOException(e);
		}
		
		return inserted;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.music4point0.dao.GenericDAO#update(com.neurologic.music4point0.entity.base.Entity)
	 */
	public boolean update(T entity) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity.");
		}
		
		if (entity.isNew()) {
			throw new DAOException("Entity is a new entity (it doesn't have a primary key).");
		}
		
		boolean updated = false;
		try {
			entityManager.merge(entity);
			entityManager.flush();
			entityManager.clear();
			updated = true;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			updated = false;
			throw new DAOException(e);
		}
		
		return updated;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#updateInBatch(za.co.sindi.persistence.entity.Entity[])
	 */
	@Override
	public boolean updateInBatch(T[] entities) throws DAOException {
		// TODO Auto-generated method stub
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		if (entities == null) {
			throw new IllegalArgumentException("Cannot update a null entities.");
		}
		
		boolean updated = false;
		try {
			for (T entity : entities) {
				if (entity.isNew()) {
					throw new DAOException("Entity is a new entity (it doesn't have a primary key).");
				}
				
				entityManager.merge(entity);
			}
			
			entityManager.flush();
			entityManager.clear();
			updated = true;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			updated = false;
			throw new DAOException(e);
		}
		
		return updated;
	}
	
	private List<Order> toJPAOrders(SortOrder[] sortOrders, Root<?> root, CriteriaBuilder criteriaBuilder) {
		
		List<Order> orders;
		if (sortOrders == null) {
			orders = Collections.emptyList();
		} else {
			orders = new ArrayList<>();
		}
		
		for (SortOrder sortOrder : sortOrders) {
			orders.add(toJPAOrder(sortOrder, root, criteriaBuilder));
		}
		
		return Collections.unmodifiableList(orders);
	}
	
	private TypedQuery<T> createQueryBySpecification(final JPASpecification<T> specification, final SortOrder... sortOrders) {
		final EntityManager entityManager = getEntityManager();
		
		if (entityManager == null) {
			throw new DAOException(NULL_ENTITYMANAGER);
		}
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistentClass);
		Root<T> root = query.from(persistentClass);
		query.where(specification.toPredicate(root, builder));
		
		if (sortOrders != null && sortOrders.length > 0) {
			query.orderBy(toJPAOrders(sortOrders, root, builder));
		}
		
		return entityManager.createQuery(query);
	}
	
	private Order toJPAOrder(SortOrder sortOrder, Root<?> root, CriteriaBuilder criteriaBuilder) {
		
		Expression<?> expression = null;
		String pathString = sortOrder.getPropertyName();
		String[] pathElements = pathString.split("\\.");
		int pathSize = pathElements.length;
		
		if (pathSize > 1) {
		    Join<Object, Object> path = root.join(pathElements[0]);
		    for (int i = 1; i < pathSize - 1; i++) {
		        path = path.join(pathElements[i]);
		    }
		
		    expression = path.get(pathElements[pathSize - 1]);
		} else {
		    expression = root.get(pathString);
		}
		
		return sortOrder.isAscending() ? criteriaBuilder.asc(expression) : criteriaBuilder.desc(expression);
	}

//	protected Collection<T> search(CriteriaQuery<T> criterion) throws DAOException {
//		if (criterion == null) {
//			throw new IllegalArgumentException("Null criterion provided.");
//		}
//		
//		final EntityManager entityManager = getEntityManager();
//		
//		if (entityManager == null) {
//			throw new DAOException(NULL_ENTITYMANAGER);
//		}
//		
//		TypedQuery<T> query = entityManager.createQuery(criterion);
//		return query.getResultList();
//	}
	
	protected abstract EntityManager getEntityManager();
}
