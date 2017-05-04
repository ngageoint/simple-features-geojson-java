package mil.nga.sf.projection;

import java.lang.reflect.Field;

import mil.nga.sf.srs.SpatialReferenceSystem;
import mil.nga.sf.util.SFException;

import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.units.Unit;
import org.osgeo.proj4j.units.Units;

/**
 * Single EPSG Projection
 * 
 * @author osbornb
 */
public class Projection {

	/**
	 * EPSG code
	 */
	private final long epsg;

	/**
	 * Coordinate Reference System
	 */
	private final CoordinateReferenceSystem crs;

	/**
	 * Constructor
	 * 
	 * @param epsg
	 *            epsg
	 * @param crs
	 *            crs
	 */
	Projection(long epsg, CoordinateReferenceSystem crs) {
		this.epsg = epsg;
		this.crs = crs;
	}

	/**
	 * Get the EPSG code
	 * 
	 * @return epsg
	 */
	public long getEpsg() {
		return epsg;
	}

	/**
	 * Get the Coordinate Reference System
	 * 
	 * @return Coordinate Reference System
	 */
	public CoordinateReferenceSystem getCrs() {
		return crs;
	}

	/**
	 * Get the transformation from this Projection to the EPSG code. Each thread
	 * of execution should have it's own transformation.
	 * 
	 * @param epsg
	 *            epsg
	 * @return transform
	 */
	public ProjectionTransform getTransformation(long epsg) {
		Projection projectionTo = ProjectionFactory.getProjection(epsg);
		return getTransformation(projectionTo);
	}

	/**
	 * Get the transformation from this Projection to the Spatial Reference
	 * System. Each thread of execution should have it's own transformation.
	 * 
	 * @param srs
	 *            spatial reference system
	 * @return projection transform
	 * @since 1.1.8
	 */
	public ProjectionTransform getTransformation(SpatialReferenceSystem srs) {
		Projection projectionTo = ProjectionFactory.getProjection(srs);
		return getTransformation(projectionTo);
	}

	/**
	 * Get the transformation from this Projection to the provided projection.
	 * Each thread of execution should have it's own transformation.
	 * 
	 * @param projection
	 *            projection
	 * @return transform
	 */
	public ProjectionTransform getTransformation(Projection projection) {
		return new ProjectionTransform(this, projection);
	}

	/**
	 * Convert the value to meters
	 * 
	 * @param value
	 *            value
	 * @return meters
	 */
	public double toMeters(double value) {
		return value / crs.getProjection().getFromMetres();
	}

	/**
	 * Get the units of this projection
	 * 
	 * @return the projection unit
	 * @since 1.2.0
	 */
	public Unit getUnit() {
		Unit unit = null;
		try {
			// The unit is currently not publicly available, use reflection
			Field f = org.osgeo.proj4j.proj.Projection.class
					.getDeclaredField("unit");
			f.setAccessible(true);
			unit = (Unit) f.get(crs.getProjection());
			if (unit == null) {
				unit = Units.METRES;
			}
		} catch (Exception e) {
			throw new SFException(
					"Failed to get projection unit for epsg: " + epsg, e);
		}
		return unit;
	}

}
