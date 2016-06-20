/**
 * 
 */
package za.co.sindi.persistence;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.common.utils.Strings;

/**
 * @author Buhake Sindi
 * @since 24 October 2015
 *
 */
public final class SortOrder {

	public static SortOrder ascending(final String propertyName) {
		return new SortOrder(propertyName, true);
	}
	
	public static SortOrder descending(final String propertyName) {
		return new SortOrder(propertyName, false);
	}
	
	private String propertyName;
	private boolean ascending;
	
	/**
	 * @param propertyName
	 * @param ascending
	 */
	private SortOrder(String propertyName, boolean ascending) {
		super();
		PreConditions.checkArgument(!Strings.isNullOrEmpty(propertyName), "A property name must not be null or empty.");
		
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the ascending
	 */
	public boolean isAscending() {
		return ascending;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ascending ? 1231 : 1237);
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SortOrder other = (SortOrder) obj;
		if (ascending != other.ascending)
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		return true;
	}
}
