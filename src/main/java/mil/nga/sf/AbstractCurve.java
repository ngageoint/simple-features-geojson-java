package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

/**
 * The base type for all 1-dimensional geometry types. A 1-dimensional geometry
 * is a geometry that has a length, but no area. A curve is considered simple if
 * it does not intersect itself (except at the start and end point). A curve is
 * considered closed its start and end point are coincident. A simple, closed
 * curve is called a ring.
 * 
 * @author osbornb
 */
abstract class AbstractCurve extends AbstractGeometry implements Curve {

	/**
	 * List of points
	 */
	private List<Position> positions = new ArrayList<Position>();

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
	protected AbstractCurve(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Curve#getPositions()
	 */
	@Override
	public List<Position> getPositions() {
		return positions;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Curve#setPositions(java.util.List)
	 */
	@Override
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Curve#addPosition(mil.nga.sf.Position)
	 */
	@Override
	public void addPosition(Position pos) {
		positions.add(pos);
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Curve#numPositions()
	 */
	@Override
	public int numPositions() {
		return positions.size();
	}

	@Override
	public boolean isEmpty() {
		return positions.isEmpty();
	}
	
	public boolean isRing() {
		if (positions.isEmpty()){
			return false;
		} else {
			return positions.get(0).equals(positions.get(positions.size() - 1));
		}
	}
}
