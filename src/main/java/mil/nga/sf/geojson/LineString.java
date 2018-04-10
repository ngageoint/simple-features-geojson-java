package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Line String
 * 
 * @author yutzlejp
 */
public class LineString extends GeoJsonObject implements Geometry,
		Coordinates<Position> {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = -2756190074121514366L;

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
	 * {@inheritDoc}
	 */
	@Override
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<Position> getCoordinates() {
		List<Position> positions = new ArrayList<Position>();
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
		List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
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
