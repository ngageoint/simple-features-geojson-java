package mil.nga.sf;

/**
 * The base type for all 2-dimensional geometry types. A 2-dimensional geometry
 * is a geometry that has an area.
 * 
 * @author yutzlerj
 */
public abstract class Surface extends Geometry {

	protected Surface(GeometryType geometryType, boolean hasZ, boolean hasM) {
		super(geometryType, hasZ, hasM);
	}
}
