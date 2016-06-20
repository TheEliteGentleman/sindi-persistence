/**
 * 
 */
package za.co.sindi.persistence.cache;

import java.util.Map;
import java.util.Set;

/**
 * @author Bienfait Sindi
 * @since 31 January 2014
 *
 */
public interface Cache<K, V> {

	public boolean containsKey(K key);
	
	public boolean containsValue(V value);
	
	public V get(K key);
	
	public V getAndRemove(K key);
	
	public Map<K, V> getAll(Set<? extends K> keys);
	
	public long getElementSize();
	
	public Set<K> getKeys();
	
	public void put(K key, V value);
	
	public void putAll(Map<? extends K, ? extends V> map);
	
	public void putIfAbsent(K key, V value);
	
	public boolean replace(K key, V oldValue, V newValue);
	
	public boolean remove(K key);
	
	public boolean removeValue(V value);
	
	public boolean removeAll();
}
