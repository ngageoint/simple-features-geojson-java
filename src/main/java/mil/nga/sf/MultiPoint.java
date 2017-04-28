package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

/**
 * A restricted form of GeometryCollection where each Geometry in the collection
 * must be of type Point.
 * 
 * @author osbornb
 */
public class MultiPoint extends Geometry {

	private List<Position> positions = new ArrayList<Position>();
	
	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiPoint(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTIPOINT, hasZ, hasM);
	}

	/**
	 * Constructor
	 * 
	 * @param positions
	 *            A list of positions, which will be used to determine hasZ and hasM
	 */
	public MultiPoint(List<Position> positions) {
		super(GeometryType.MULTIPOINT, Position.hasZ(positions), Position.hasM(positions));
		setPositions(positions);
	}

	/**
	 * Add a position
	 * 
	 * @param pos
	 *            position
	 */
	public void addPosition(Position pos) {
		this.positions.add(pos);
	}

	/**
	 * Get the number of positions
	 * 
	 * @return number of positions
	 */
	public int numPositions() {
		return this.positions.size();
	}

	/**
	 * Get the positions
	 * 
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * Set the positions
	 * 
	 * @param positions the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	@Override
	public boolean isEmpty() {
		return this.positions.isEmpty();
	}

}
