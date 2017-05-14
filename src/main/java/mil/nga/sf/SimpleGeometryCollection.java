package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of zero or more Geometry instances.
 * 
 * @author osbornb
 */
public class SimpleGeometryCollection<T extends Geometry> extends AbstractGeometry implements GeometryCollection<T> {

	/**
	 * List of geometries
	 */
	private List<T> geometries = new ArrayList<T>();

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public SimpleGeometryCollection(boolean hasZ, boolean hasM) {
		super(GeometryType.GEOMETRYCOLLECTION, hasZ, hasM);
	}

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
	protected SimpleGeometryCollection(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.GeometryCollection#getGeometries()
	 */
	@Override
	public List<T> getGeometries() {
		return geometries;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.GeometryCollection#setGeometries(java.util.List)
	 */
	@Override
	public void setGeometries(List<T> geometries) {
		this.geometries = geometries;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.GeometryCollection#addGeometry(T)
	 */
	@Override
	public void addGeometry(T geometry) {
		geometries.add(geometry);
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.GeometryCollection#numGeometries()
	 */
	@Override
	public int numGeometries() {
		return geometries.size();
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.GeometryCollection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return geometries.isEmpty();
	}

}
