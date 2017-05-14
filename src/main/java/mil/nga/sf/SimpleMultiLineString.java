package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PositionUtils;

/**
 * A restricted form of MultiCurve where each Curve in the collection must be of
 * type LineString.
 * 
 * @author osbornb
 */
public class SimpleMultiLineString extends AbstractGeometryCollection<LineString> implements MultiLineString {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public SimpleMultiLineString(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTILINESTRING, hasZ, hasM);
	}

	public SimpleMultiLineString(List<LineString> lineStrings) {
		super(GeometryType.MULTILINESTRING, PositionUtils.hasZ(lineStrings), PositionUtils.hasM(lineStrings));
		setLineStrings(lineStrings);
	}

	/**
	 * Get the line strings
	 * 
	 * @return line strings
	 */
	public List<LineString> getLineStrings() {
		return getGeometries();
	}

	/**
	 * Set the line string
	 * 
	 * @param lineStrings
	 *            line strings
	 */
	public void setLineStrings(List<LineString> lineStrings) {
		setGeometries(lineStrings);
	}

	/**
	 * Add a line string
	 * 
	 * @param lineString
	 *            line string
	 */
	public void addLineString(LineString lineString) {
		addGeometry(lineString);
	}

	/**
	 * Get the number of line strings
	 * 
	 * @return number of line strings
	 */
	public int numLineStrings() {
		return numGeometries();
	}
}
