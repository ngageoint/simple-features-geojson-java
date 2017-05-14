package mil.nga.sf;

import java.util.List;

public interface CurvePolygon extends Surface {

	/**
	 * Add a ring
	 * 
	 * @param ring
	 *            ring
	 */
	public void addRing(LinearRing ring);

	/**
	 * Get the rings
	 * 
	 * @return rings
	 */
	public List<LinearRing> getRings();

	/**
	 * Get the number of rings
	 * 
	 * @return number of rings
	 */
	public int numRings();

	/**
	 * Set the rings
	 * 
	 * @param rings
	 *            rings
	 */
	public void setRings(List<LinearRing> rings);

}
