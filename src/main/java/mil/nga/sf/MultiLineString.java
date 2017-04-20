package mil.nga.sf;

import java.util.List;

/**
 * A restricted form of MultiCurve where each Curve in the collection must be of
 * type LineString.
 * 
 * @author osbornb
 */
public class MultiLineString extends MultiCurve<LineString> {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiLineString(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTILINESTRING, hasZ, hasM);
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
