/**
 * 
 */
package za.co.sindi.persistence.entity;

import java.io.Serializable;

/**
 * @author Bienfait Sindi
 * @since 25 November 2012
 *
 */
public interface DomainObject extends Serializable {
	
	/**
	 * 
	 * @return {@code true} if the entity has never been persisted, {@code false} otherwise.
	 */
	public boolean isNew();
	
}
