package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.util.SFException;

/**
 * A planar surface defined by an exterior ring and zero or more interior ring.
 * Each ring is defined by a Curve instance.
 * 
 * @author osbornb
 */
public class SimpleCurvePolygon extends AbstractGeometry implements CurvePolygon {

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
	public SimpleCurvePolygon(boolean hasZ, boolean hasM) {
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
	protected SimpleCurvePolygon(GeometryType type, boolean hasZ, boolean hasM) {
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

	@Override
	public List<CurvePolygon> getPolygons() {
		List<CurvePolygon> result = new ArrayList<CurvePolygon>();
		result.add(this);
		return result;
	}

	@Override
	public void setPolygons(List<CurvePolygon> polygons) {
		if (polygons == null){
			setRings(new ArrayList<LinearRing>());
		} else if (polygons.size() == 1) {
			setRings(polygons.get(0).getRings());
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPolygon(CurvePolygon polygon) {
		if ((rings == null) || rings.isEmpty()){
			setRings(polygon.getRings());
		} else {
			throw new SFException("A SimpleCurvePolygon can only have a single polygon.");
		}
	}

	@Override
	public int numPolygons() {
		// TODO Auto-generated method stub
		return 0;
	}
}
