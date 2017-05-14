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
	 * List of curves
	 */
	private List<Curve> curves = new ArrayList<Curve>();

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
	public List<Curve> getCurves() {
		return curves;
	}

	/**
	 * Set the curves
	 * 
	 * @param curves
	 *            surves
	 */
	public void setCurves(List<Curve> curves) {
		this.curves = curves;
	}

	/**
	 * Add a line string
	 * 
	 * @param curve
	 *            line string
	 */
	public void addCurve(Curve curve) {
		curves.add(curve);
	}

	/**
	 * Get the number of curves
	 * 
	 * @return number of curves
	 */
	public int numCurves() {
		return curves.size();
	}

	@Override
	public boolean isEmpty() {
		return curves.isEmpty();
	}
}
