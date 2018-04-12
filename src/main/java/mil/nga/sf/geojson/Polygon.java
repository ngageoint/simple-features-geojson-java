package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.util.GeometryUtils;

/**
 * Polygon
 * 
 * @author yutzlejp
 */
public class Polygon extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Simple polygon
	 */
	private mil.nga.sf.Polygon polygon;

	/**
	 * Constructor
	 */
	public Polygon() {
	}

	/**
	 * Constructor
	 * 
	 * @param positions
	 *            list of positions
	 */
	public Polygon(List<List<Position>> positions) {
		setCoordinates(positions);
	}

	/**
	 * Constructor
	 * 
	 * @param polygon
	 *            simple polygon
	 */
	public Polygon(mil.nga.sf.Polygon polygon) {
		this.polygon = polygon;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	public List<List<Position>> getCoordinates() {
		List<List<Position>> result = new ArrayList<>();
		for (mil.nga.sf.LineString ring : polygon.getRings()) {
			List<Position> positions = new ArrayList<>();
			for (mil.nga.sf.Point pos : ring.getPoints()) {
				positions.add(new Position(pos));
			}
			result.add(positions);
		}
		return result;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * 
	 * @param positionList
	 *            position list
	 */
	private void setCoordinates(List<List<Position>> positionList) {
		List<mil.nga.sf.LineString> rings = new ArrayList<>();
		for (List<Position> ringPositions : positionList) {
			List<mil.nga.sf.Point> points = new ArrayList<>();
			for (Position position : ringPositions) {
				points.add(position.toSimplePoint());
			}
			mil.nga.sf.LinearRing ring = new mil.nga.sf.LinearRing(
					GeometryUtils.hasZ(points), GeometryUtils.hasM(points));
			ring.setPoints(points);
			rings.add(ring);
		}
		if (polygon == null) {
			polygon = new mil.nga.sf.Polygon(rings);
		} else {
			polygon.setRings(rings);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return polygon;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "Polygon";
	}

}
