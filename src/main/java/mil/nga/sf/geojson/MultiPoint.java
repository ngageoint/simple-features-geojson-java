package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi Point
 * 
 * @author yutzlejp
 */
public class MultiPoint extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Simple multi point
	 */
	private mil.nga.sf.MultiPoint multiPoint;

	/**
	 * Constructor
	 */
	public MultiPoint() {
	}

	/**
	 * Constructor
	 * 
	 * @param positions
	 *            list of positions
	 */
	public MultiPoint(List<Position> positions) {
		setCoordinates(positions);
	}

	/**
	 * Constructor
	 * 
	 * @param multiPoint
	 *            simple multi point
	 */
	public MultiPoint(mil.nga.sf.MultiPoint multiPoint) {
		this.multiPoint = multiPoint;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	public List<Position> getCoordinates() {
		List<Position> positions = new ArrayList<>();
		for (mil.nga.sf.Point point : multiPoint.getGeometries()) {
			positions.add(new Position(point));
		}
		return positions;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * 
	 * @param positions
	 *            list of positions
	 */
	private void setCoordinates(List<Position> positions) {
		List<mil.nga.sf.Point> points = new ArrayList<>();
		for (Position pos : positions) {
			points.add(pos.toSimplePoint());
		}
		if (multiPoint == null) {
			multiPoint = new mil.nga.sf.MultiPoint(points);
		} else {
			multiPoint.setGeometries(points);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return multiPoint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "MultiPoint";
	}

}
