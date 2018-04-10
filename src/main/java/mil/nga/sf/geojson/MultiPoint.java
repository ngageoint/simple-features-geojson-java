package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Multi Point
 * 
 * @author yutzlejp
 */
public class MultiPoint extends GeoJsonObject implements Geometry,
		Coordinates<Position> {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = -3432834802398357952L;

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
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<Position> getCoordinates() {
		List<Position> positions = new ArrayList<Position>();
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
		List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
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
