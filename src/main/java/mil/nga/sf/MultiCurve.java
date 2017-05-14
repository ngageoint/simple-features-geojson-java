package mil.nga.sf;

import java.util.List;

/**
 * A restricted form of GeometryCollection where each Geometry in the collection
 * must be of type Curve.
 * 
 * @author osbornb
 */
public class MultiCurve extends AbstractGeometry implements GeometryCollection<Curve> {

	/**
	 * Constructor
	 * 
	 * @param type
	 *            geometry type
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	protected MultiCurve(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTICURVE, hasZ, hasM);
	}

	@Override
	public List<Curve> getGeometries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGeometries(List<Curve> geometries) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGeometry(Curve geometry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int numGeometries() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
