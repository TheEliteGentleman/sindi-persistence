/**
 * 
 */
package za.co.sindi.persistence.dao;


/**
 * @author Bienfait Sindi
 * @since 21 March 2015
 *
 */
public abstract class DAOManager {

	private static ThreadLocal<DAOManager> instanceThread = new ThreadLocal<DAOManager>();
	
	/**
	 * 
	 */
	protected DAOManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static DAOManager getInstance() {
		return instanceThread.get();
	}
	
	protected static void setInstance(DAOManager instance) {
		if (instance != null) {
			instanceThread.set(instance);
		}
	}
	
	public abstract void close() throws Exception;
}
