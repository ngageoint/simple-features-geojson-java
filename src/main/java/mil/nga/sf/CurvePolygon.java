package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

/**
 * A planar surface defined by an exterior ring and zero or more interior ring.
 * Each ring is defined by a Curve instance.
 * 
 * @author osbornb
 */
public class CurvePolygon extends Surface {

	/**
	 * List of rings
	 */
	private List<LinearRing> rings = new ArrayList<LinearRing>();

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public CurvePolygon(boolean hasZ, boolean hasM) {
		super(GeometryType.CURVEPOLYGON, hasZ, hasM);
	}

	/**
	 * Constructor
	 * 
	 * @param type
	 *            geometry type
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	protected CurvePolygon(GeometryType type, boolean hasZ, boolean hasM) {
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
		return rings.isEmpty();
	}
}
