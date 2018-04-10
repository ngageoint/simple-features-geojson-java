package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.util.GeometryUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Multi Line String
 * 
 * @author yutzlejp
 */
public class MultiLineString extends Geometry implements 
		Coordinates<List<Position>> {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = -2752279881315034003L;

	/**
	 * Simple Multi Line String
	 */
	private mil.nga.sf.MultiLineString multiLineString;

	/**
	 * Constructor
	 */
	public MultiLineString() {
	}

	/**
	 * Constructor
	 * 
	 * @param positions
	 *            position list
	 */
	public MultiLineString(List<List<Position>> positions) {
		setCoordinates(positions);
	}

	/**
	 * Constructor
	 * 
	 * @param multiLineString
	 *            simple multi line string
	 */
	public MultiLineString(mil.nga.sf.MultiLineString multiLineString) {
		this.multiLineString = multiLineString;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<List<Position>> getCoordinates() {
		List<List<Position>> result = new ArrayList<List<Position>>();
		for (mil.nga.sf.LineString lineString : multiLineString.getGeometries()) {
			List<Position> positions = new ArrayList<Position>();
			for (mil.nga.sf.Point point : lineString.getPoints()) {
				positions.add(new Position(point));
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
		List<mil.nga.sf.LineString> lineStrings = new ArrayList<mil.nga.sf.LineString>();
		for (List<Position> lineStringPositions : positionList) {
			List<mil.nga.sf.Point> positions = new ArrayList<mil.nga.sf.Point>();
			for (Position position : lineStringPositions) {
				positions.add(position.toSimplePoint());
			}
			mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(
					GeometryUtils.hasZ(positions),
					GeometryUtils.hasM(positions));
			lineString.setPoints(positions);
			lineStrings.add(lineString);
		}
		if (multiLineString == null) {
			multiLineString = new mil.nga.sf.MultiLineString(lineStrings);
		} else {
			multiLineString.setGeometries(lineStrings);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return multiLineString;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "MultiLineString";
	}

}
