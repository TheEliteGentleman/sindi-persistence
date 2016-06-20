/**
 * 
 */
package za.co.sindi.persistence.audit;

import java.util.Date;

/**
 * @author Bienfait Sindi
 * @since 05 March 2014
 *
 */
public interface TemporalAudit {

	public Date getCreationTimestamp();
	public void setCreationTimestamp(final Date creationTimestamp);
	public Date getDeletionTimestamp();
	public void setDeletionTimestamp(final Date deletionTimestamp);
	public Date getLastModificationTimestamp();
	public void setLastModificationTimestamp(final Date lastModificationTimestamp);
}
