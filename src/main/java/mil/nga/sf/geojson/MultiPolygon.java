package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.util.GeometryUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Multi Polygon
 * 
 * @author yutzlejp
 */
public class MultiPolygon extends Geometry implements
		Coordinates<List<List<Position>>> {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Simple multi polygon
	 */
	private mil.nga.sf.MultiPolygon multiPolygon;

	/**
	 * Constructor
	 */
	public MultiPolygon() {
	}

	/**
	 * Constructor
	 * 
	 * @param positions
	 *            position list
	 */
	public MultiPolygon(List<List<List<Position>>> positions) {
		setCoordinates(positions);
	}

	/**
	 * Constructor
	 * 
	 * @param multiPolygon
	 *            simple multi polygon
	 */
	public MultiPolygon(mil.nga.sf.MultiPolygon multiPolygon) {
		this.multiPolygon = multiPolygon;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<List<List<Position>>> getCoordinates() {
		List<List<List<Position>>> result = new ArrayList<>();
		for (mil.nga.sf.Polygon polygon : multiPolygon.getGeometries()) {
			List<List<Position>> polygonPositions = new ArrayList<>();
			for (mil.nga.sf.LineString ring : polygon.getRings()) {
				List<Position> positions = new ArrayList<>();
				for (mil.nga.sf.Point pos : ring.getPoints()) {
					positions.add(new Position(pos));
				}
				polygonPositions.add(positions);
			}
			result.add(polygonPositions);
		}
		return result;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * 
	 * @param positionList
	 *            position list
	 */
	private void setCoordinates(List<List<List<Position>>> positionList) {
		List<mil.nga.sf.Polygon> polygons = new ArrayList<>();
		for (List<List<Position>> polygonPositions : positionList) {
			List<mil.nga.sf.LineString> rings = new ArrayList<>();
			for (List<Position> ringPositions : polygonPositions) {
				List<mil.nga.sf.Point> points = new ArrayList<>();
				for (Position position : ringPositions) {
					points.add(position.toSimplePoint());
				}
				mil.nga.sf.LinearRing ring = new mil.nga.sf.LinearRing(
						GeometryUtils.hasZ(points), GeometryUtils.hasM(points));
				ring.setPoints(points);
				rings.add(ring);
			}
			mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
			polygons.add(polygon);
		}
		if (multiPolygon == null) {
			multiPolygon = new mil.nga.sf.MultiPolygon(polygons);
		} else {
			multiPolygon.setGeometries(polygons);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return multiPolygon;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "MultiPolygon";
	}

}
