/**
 * 
 */
package za.co.sindi.persistence.pagination;

/**
 * @author Bienfait Sindi
 * @since 11 March 2014
 *
 */
public class Page extends Range {

	private int page;
//	private int limit;

	/**
	 * @param page
	 * @param limit
	 */
	public Page(int page, int limit) {
		super((page - 1) * limit, limit);
		this.page = page;
//		this.limit = limit;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

//	/**
//	 * @return the limit
//	 */
//	public int getLimit() {
//		return limit;
//	}
//
//	/**
//	 * @param limit the limit to set
//	 */
//	public void setLimit(int limit) {
//		this.limit = limit;
//	}
}
