package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends Surface {

	/**
	 * List of rings
	 */
	private List<LinearRing> rings = new ArrayList<LinearRing>();

	/**
	 * Constructor 
	 * 
	 * @param hasZ
	 * @param hasM
	 */
	public Polygon(boolean hasZ, boolean hasM) {
		super(GeometryType.POLYGON, hasZ, hasM);
	}

	/**
	 * Constructor for overrides
	 * 
	 * @param hasZ
	 * @param hasM
	 */
	protected Polygon(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	/**
	 * Get the rings
	 * 
	 * @return rings
	 */
	public List<LinearRing> getRings() {
		return rings;
	}

	/**
	 * Set the rings
	 * 
	 * @param rings
	 *            rings
	 */
	public void setRings(List<LinearRing> rings) {
		this.rings = rings;
	}

	/**
	 * Add a ring
	 * 
	 * @param ring
	 *            ring
	 */
	public void addRing(LinearRing ring) {
		rings.add(ring);
	}

	/**
	 * Get the number of rings
	 * 
	 * @return number of rings
	 */
	public int numRings() {
		return rings.size();
	}

	@Override
	public boolean isEmpty() {
		return (rings == null) || rings.isEmpty();
	}
}
