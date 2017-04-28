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
public abstract class Curve extends Geometry {

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
	protected Curve(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	/**
	 * Get the positions
	 * 
	 * @return positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * Set the positions
	 * 
	 * @param positions
	 *            positions
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/**
	 * Add a position
	 * 
	 * @param pos
	 *            position
	 */
	public void addPosition(Position pos) {
		positions.add(pos);
	}

	/**
	 * Get the number of positions
	 * 
	 * @return number of positions
	 */
	public int numPositions() {
		return positions.size();
	}

	@Override
	public boolean isEmpty() {
		return positions.isEmpty();
	}
	
	protected static boolean hasZ(List<Position> positions){
		boolean result = false;
		for(Position position : positions){
			if (position.getZ() != null) {
				result = true;
				break;
			}
		}
		return result;
	}

	protected static boolean hasM(List<Position> positions){
		boolean result = false;
		for(Position position : positions){
			if (position.getM() != null) {
				result = true;
				break;
			}
		}
		return result;
	}
}
