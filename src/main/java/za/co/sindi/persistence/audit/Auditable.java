/**
 * 
 */
package za.co.sindi.persistence.audit;

/**
 * @author Bienfait Sindi
 * @since 07 March 2014
 *
 */
public interface Auditable<U> extends TemporalAudit, IdentityAudit<U> {

}
