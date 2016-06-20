/**
 * 
 */
package za.co.sindi.persistence.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import za.co.sindi.persistence.entity.Entity;
import za.co.sindi.persistence.exception.DAOException;
import za.co.sindi.sql.handler.ResultSetHandler;
import za.co.sindi.sql.query.QueryManager;
import za.co.sindi.sql.query.QueryManagerFactory;
import za.co.sindi.sql.query.SelectableQuery;
import za.co.sindi.sql.query.impl.DefaultQueryManagerFactory;

/**
 * @author Bienfait Sindi
 * @since 10 July 2010
 * 
 */
public abstract class JDBCGenericDAO<T extends Entity<PK>, PK extends Serializable> extends AbstractGenericDAO<T, PK> {

	private Connection connection;
	private String schema;
	private String tableName;

	/**
	 * @param connection
	 * @param tableName
	 */
	protected JDBCGenericDAO(Connection connection, String tableName) {
		this(connection, null, tableName);
	}

	/**
	 * @param connection
	 * @param schema
	 * @param tableName
	 */
	protected JDBCGenericDAO(Connection connection, String schema, String tableName) {
		super();
		
		if (tableName == null || tableName.isEmpty()) {
			throw new IllegalArgumentException("A Database table name is required.");
		}
		
		this.connection = connection;
		this.schema = (schema == null) ? "" : schema.trim();
		this.tableName = tableName.trim();
	}

	/**
	 * @return the connection
	 */
	protected Connection getConnection() {
		return connection;
	}

	/**
	 * @return the schema
	 */
	protected String getSchema() {
		return schema;
	}

	/**
	 * @return the tableName
	 */
	protected String getTableName() {
		return tableName;
	}

	protected final String getFullTableName() {
		StringBuilder sb = new StringBuilder();
		if (!schema.isEmpty()) {
			sb.append(schema).append('.');
		}
		sb.append(tableName);
		return sb.toString(); 
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.GenericDAO#countAll()
	 */
	@Override
	public long countAll() throws DAOException {
		QueryManagerFactory factory = new DefaultQueryManagerFactory();
		QueryManager manager = factory.createQueryManager(getConnection()); //connection
		SelectableQuery<Long> selectQuery = manager.createSelectableQuery("SELECT COUNT(*) AS TOTAL FROM " + getFullTableName());
		selectQuery.setResultSetHandler(new ResultSetHandler<Long>() {

			/* (non-Javadoc)
			 * @see za.co.sindi.sql.ResultSetHandler#handle(java.sql.ResultSet)
			 */
			@Override
			public Long handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				long count = 0;
				if (rs.next()) {
					count = rs.getLong("TOTAL");
				}
				
				return count;
			}
		});
		selectQuery.execute();
		return selectQuery.getResult();
	}
	
//	protected abstract Connection getConnection() throws SQLException;
}
