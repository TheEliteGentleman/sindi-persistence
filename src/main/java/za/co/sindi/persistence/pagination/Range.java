/**
 * 
 */
package za.co.sindi.persistence.pagination;

/**
 * @author Bienfait Sindi
 * @since 11 March 2014
 *
 */
public class Range implements Pagination {

	private int startPosition;
	private int range;
	
	/**
	 * @param startPosition
	 * @param range
	 */
	public Range(int startPosition, int range) {
		super();
		
		if (startPosition < 0) {
			throw new IllegalArgumentException("Start position may not be less than 0.");
		}
		
		if (range < 0) {
			throw new IllegalArgumentException("Range may not be less than 0.");
		}
		
		this.startPosition = startPosition;
		this.range = range;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.pagination.Pagination#getStartPosition()
	 */
	@Override
	public int getStartPosition() {
		// TODO Auto-generated method stub
		return startPosition;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.pagination.Pagination#getRange()
	 */
	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return range;
	}
}
