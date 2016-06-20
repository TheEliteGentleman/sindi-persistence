/**
 * 
 */
package za.co.sindi.persistence.cache;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Bienfait Sindi
 * @since 26 June 2014
 *
 */
public interface CacheManager {

	public boolean cacheExists(String cacheName);
	public void close();
	public <K, V> Cache<K, V> getCache(String cacheName);
	public Collection<Cache<? extends Serializable, ? extends Serializable>> getCaches();
	public String getName();
	public boolean isClosed();
}
