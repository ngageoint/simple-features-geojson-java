package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Multi Line String
 * 
 * @author yutzlejp
 */
public class MultiLineString extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * List of line strings
	 */
	private List<LineString> lineStrings = null;

	/**
	 * Create a multi line string from coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 * @return multi line string
	 * @since 3.0.0
	 */
	public static MultiLineString fromCoordinates(
			List<List<Position>> coordinates) {
		MultiLineString multiLineString = new MultiLineString();
		multiLineString.setCoordinates(coordinates);
		return multiLineString;
	}

	/**
	 * Constructor
	 */
	public MultiLineString() {

	}

	/**
	 * Constructor
	 * 
	 * @param lineStrings
	 *            line string list
	 * @since 3.0.0
	 */
	public MultiLineString(List<LineString> lineStrings) {
		this.lineStrings = lineStrings;
	}

	/**
	 * Constructor
	 * 
	 * @param multiLineString
	 *            simple multi line string
	 */
	public MultiLineString(mil.nga.sf.MultiLineString multiLineString) {
		setMultiLineString(multiLineString);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeometryType getGeometryType() {
		return GeometryType.MULTILINESTRING;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return getMultiLineString();
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	public List<List<Position>> getCoordinates() {
		List<List<Position>> coordinates = new ArrayList<>();
		for (LineString lineString : lineStrings) {
			coordinates.add(lineString.getCoordinates());
		}
		return coordinates;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * 
	 * @param coordinates
	 *            coordinates
	 * @since 3.0.0
	 */
	public void setCoordinates(List<List<Position>> coordinates) {
		lineStrings = new ArrayList<>();
		for (List<Position> positions : coordinates) {
			lineStrings.add(LineString.fromCoordinates(positions));
		}
	}

	/**
	 * Get the line strings
	 * 
	 * @return list of line strings
	 * @since 3.0.0
	 */
	@JsonIgnore
	public List<LineString> getLineStrings() {
		return lineStrings;
	}

	/**
	 * Set the line strings
	 * 
	 * @param lineStrings
	 *            list of line strings
	 * @since 3.0.0
	 */
	public void setLineStrings(List<LineString> lineStrings) {
		this.lineStrings = lineStrings;
	}

	/**
	 * Get the simple features multi line string
	 * 
	 * @return multi line string
	 * @since 3.0.0
	 */
	@JsonIgnore
	public mil.nga.sf.MultiLineString getMultiLineString() {
		List<mil.nga.sf.LineString> simpleLineStrings = new ArrayList<>();
		for (LineString lineString : lineStrings) {
			simpleLineStrings.add(lineString.getLineString());
		}
		return new mil.nga.sf.MultiLineString(simpleLineStrings);
	}

	/**
	 * Set the simple features multi line string
	 * 
	 * @param multiLineString
	 *            multi line string
	 * @since 3.0.0
	 */
	public void setMultiLineString(mil.nga.sf.MultiLineString multiLineString) {
		lineStrings = new ArrayList<>();
		for (mil.nga.sf.LineString lineString : multiLineString
				.getLineStrings()) {
			lineStrings.add(new LineString(lineString));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((lineStrings == null) ? 0 : lineStrings.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiLineString other = (MultiLineString) obj;
		if (lineStrings == null) {
			if (other.lineStrings != null)
				return false;
		} else if (!lineStrings.equals(other.lineStrings))
			return false;
		return true;
	}

}
