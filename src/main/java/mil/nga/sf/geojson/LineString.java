package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

/**
 * Line String
 * 
 * @author yutzlejp
 */
public class LineString extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Simple Line String
	 */
	private mil.nga.sf.LineString lineString;

	/**
	 * Constructor
	 */
	public LineString() {
	}

	/**
	 * Constructor
	 * 
	 * @param positions
	 *            list of positions
	 */
	public LineString(List<Position> positions) {
		setCoordinates(positions);
	}

	/**
	 * Constructor
	 * 
	 * @param lineString
	 *            simple line string
	 */
	public LineString(mil.nga.sf.LineString lineString) {
		this.lineString = lineString;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return lineString;
	}

	/**
	 * Get the coordinates
	 * 
	 * @return list of positions
	 */
	public List<Position> getCoordinates() {
		List<Position> positions = new ArrayList<>();
		for (mil.nga.sf.Point point : lineString.getPoints()) {
			positions.add(new Position(point));
		}
		return positions;
	}

	/**
	 * Set the coordinates
	 * 
	 * @param positions
	 *            list of positions
	 */
	public void setCoordinates(List<Position> positions) {
		List<mil.nga.sf.Point> points = new ArrayList<>();
		for (Position pos : positions) {
			points.add(pos.toSimplePoint());
		}
		if (lineString == null) {
			lineString = new mil.nga.sf.LineString(points);
		} else {
			lineString.setPoints(points);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "LineString";
	}

}
