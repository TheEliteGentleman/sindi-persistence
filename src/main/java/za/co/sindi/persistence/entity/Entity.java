/**
 * 
 */
package za.co.sindi.persistence.entity;

import java.io.Serializable;

/**
 * @author Bienfait Sindi
 * @since 11 October 2012
 *
 */
public interface Entity<PK extends Serializable> extends DomainObject {

//	private PK id;

	/**
	 * @return the id
	 */
	public PK getId();

	/**
	 * @param id the id to set
	 */
	public void setId(PK id);
}
