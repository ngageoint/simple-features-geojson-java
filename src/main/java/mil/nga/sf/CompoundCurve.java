package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

/**
 * Compound Curve, Curve sub type
 * 
 * @author osbornb
 */
public class CompoundCurve extends Curve {

	/**
	 * List of line strings
	 */
	private List<LineString> lineStrings = new ArrayList<LineString>();

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public CompoundCurve(boolean hasZ, boolean hasM) {
		super(GeometryType.COMPOUNDCURVE, hasZ, hasM);
	}

	/**
	 * Get the line strings
	 * 
	 * @return line strings
	 */
	public List<LineString> getLineStrings() {
		return lineStrings;
	}

	/**
	 * Set the line strings
	 * 
	 * @param lineStrings
	 *            line strings
	 */
	public void setLineStrings(List<LineString> lineStrings) {
		this.lineStrings = lineStrings;
	}

	/**
	 * Add a line string
	 * 
	 * @param lineString
	 *            line string
	 */
	public void addLineString(LineString lineString) {
		lineStrings.add(lineString);
	}

	/**
	 * Get the number of line strings
	 * 
	 * @return number of line strings
	 */
	public int numLineStrings() {
		return lineStrings.size();
	}

}
