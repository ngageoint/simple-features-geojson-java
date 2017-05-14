package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.util.PositionUtils;

/**
 * A restricted form of MultiCurve where each Curve in the collection must be of
 * type LineString.
 * 
 * @author osbornb
 */
public class AbstractGeometryCollection<T extends Geometry> extends AbstractGeometry implements GeometryCollection<T> {

	private List<T> geometries;
	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public AbstractGeometryCollection(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	public AbstractGeometryCollection(GeometryType type, List<T> geometries) {
		super(type, PositionUtils.hasZ(geometries), PositionUtils.hasM(geometries));
		setGeometries(geometries);
	}

	@Override
	public List<T> getGeometries() {
		return geometries;
	}

	@Override
	public void setGeometries(List<T> geometries) {
		this.geometries = geometries;
	}

	@Override
	public void addGeometry(T geometry) {
		if (geometries == null) {
			geometries = new ArrayList<T>();
		}
		geometries.add(geometry);
	}

	@Override
	public int numGeometries() {
		return (geometries == null) ? 0 : geometries.size();
	}

	@Override
	public boolean isEmpty() {
		return (geometries == null) || (geometries.isEmpty());
	}

}
