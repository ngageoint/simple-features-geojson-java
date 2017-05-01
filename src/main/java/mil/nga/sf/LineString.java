package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PositionUtils;

/**
 * A Curve that connects two or more positions in space.
 * 
 * @author osbornb
 */
public class LineString extends Curve {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public LineString(boolean hasZ, boolean hasM) {
		super(GeometryType.LINESTRING, hasZ, hasM);
	}

	/**
	 * Constructor
	 * 
	 * @param positions
	 *            positions describing this LineString
	 */
	public LineString(List<Position> positions) {
		super(GeometryType.LINESTRING, PositionUtils.hasZ(positions), PositionUtils.hasM(positions));
		setPositions(positions);
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
	protected LineString(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

}
