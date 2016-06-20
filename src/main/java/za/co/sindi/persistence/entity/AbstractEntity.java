/**
 * 
 */
package za.co.sindi.persistence.entity;

import java.io.Serializable;

import za.co.sindi.common.utils.Classes;
import za.co.sindi.common.utils.Defaults;
import za.co.sindi.persistence.entity.Entity;

/**
 * @author Bienfait Sindi
 * @since 28 March 2016
 *
 */
public abstract class AbstractEntity<PK extends Serializable> implements Entity<PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7032444535531699639L;

	/* (non-Javadoc)
	 * @see za.co.sindi.entity.Entity#isNew()
	 */
	public boolean isNew() {
		// TODO Auto-generated method stub
//		return null == getId();
		PK id = getId();
		if (id == null) {
			return true;
		}
		
		Class<?> idClass = id.getClass();
		if (idClass.isPrimitive()) {
			return id.equals(Defaults.getDefaultValue(idClass));
		}
		
		Class<?> primitiveIdClass = Classes.getPrimitiveTypeFor(idClass);
		if (primitiveIdClass != null) {
			return id.equals(Defaults.getDefaultValue(primitiveIdClass));
		}
		
		return false;
	}	
}
