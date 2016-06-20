/**
 * 
 */
package za.co.sindi.persistence.dao;

import java.sql.Connection;

import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 21 March 2015
 *
 */
public abstract class JDBCDAOManager extends DAOManager {

	private Connection connection;
	
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		if (connection == null) {
			connection = createConnection();
		}
		
		return connection;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.dao.DAOManager#close()
	 */
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		SQLUtils.close(connection);
		connection = null;
	}
	
	protected abstract Connection createConnection();
}
