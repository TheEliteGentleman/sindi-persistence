/**
 * 
 */
package za.co.sindi.persistence.audit;

/**
 * @author Bienfait Sindi
 * @since 05 March 2014
 *
 */
public interface IdentityAudit<U> {

	public U getCreatedBy();
	public void setCreatedBy(final U createdBy);
	public U getDeletedBy();
	public void setDeletedBy(final U deletedBy);
	public U getLastModifiedBy();
	public void setLastModifiedBy(final U lastModifiedBy);
}
