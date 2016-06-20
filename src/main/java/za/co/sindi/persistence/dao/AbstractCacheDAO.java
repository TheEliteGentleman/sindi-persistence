/**
 * 
 */
package za.co.sindi.persistence.dao;

import java.io.Serializable;

import za.co.sindi.persistence.cache.Cache;
import za.co.sindi.persistence.entity.Entity;

/**
 * @author Bienfait Sindi
 * @since 31 January 2014
 *
 */
public abstract class AbstractCacheDAO<T extends Entity<PK>, PK extends Serializable> extends AbstractGenericDAO<T, PK> {

	private Cache<PK, T> cache;
//	private CacheManager cacheManager;
//	private String cacheName;

	/**
	 * @param cache
	 */
	protected AbstractCacheDAO(Cache<PK, T> cache) {
		super();
		if (cache == null) {
			throw new IllegalArgumentException("No cache provided.");
		}
		
		this.cache = cache;
	}

//	/**
//	 * @param cacheManager
//	 */
//	protected AbstractCacheDAO(CacheManager cacheManager, String cacheName) {
//		super();
//		if (cacheManager == null) {
//			throw new IllegalArgumentException("No cache manager provided.");
//		}
//		
//		if (StringUtils.isNullOrEmpty(cacheName)) {
//			throw new IllegalArgumentException("No cache name provided.");
//		}
//		
//		this.cacheManager = cacheManager;
//		this.cacheName = cacheName;
//	}
	
	/**
	 * @return the cache
	 */
	protected Cache<PK, T> getCache() {
//		if (cache == null) {
//			cache = cacheManager.getCache(cacheName);
//		}
		
		return cache;
	}
}
