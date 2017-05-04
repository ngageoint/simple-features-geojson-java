package mil.nga.sf.projection;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import mil.nga.sf.srs.SpatialReferenceSystem;
import mil.nga.sf.util.SFException;

import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;

/**
 * Projection factory for coordinate projections and transformations
 * 
 * @author osbornb
 */
public class ProjectionFactory {

	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(ProjectionFactory.class.getName());

	/**
	 * Mapping of EPSG projection codes to projections
	 */
	private static Map<Long, Projection> projections = new HashMap<Long, Projection>();

	/**
	 * CRS Factory
	 */
	private static final CRSFactory csFactory = new CRSFactory();

	/**
	 * Get the projection for the EPSG code
	 * 
	 * @param epsg
	 * @return projection
	 */
	public static Projection getProjection(long epsg) {
		Projection projection = projections.get(epsg);
		if (projection == null) {

			CoordinateReferenceSystem crs = null;

			// Get the projection parameters from the properties
			String parameters = null;
			if (epsg == -1 || epsg == 0) {
				parameters = ProjectionRetriever
						.getProjection(ProjectionConstants.EPSG_WORLD_GEODETIC_SYSTEM);
			} else {
				parameters = ProjectionRetriever.getProjection(epsg);
			}

			// Try to create the projection from the parameters
			if (parameters != null) {
				try {
					crs = csFactory.createFromParameters(String.valueOf(epsg),
							parameters);
				} catch (Exception e) {
					logger.log(Level.WARNING,
							"Failed to create projection for epsg " + epsg
									+ " from parameters: " + parameters, e);
				}
			}

			// If failed try to create the projection from the EPSG name
			String epsgName = null;
			if (crs == null) {
				epsgName = "EPSG:" + epsg;
				try {
					crs = csFactory.createFromName(epsgName);
				} catch (Exception e) {
					logger.log(Level.WARNING,
							"Failed to create projection from name: "
									+ epsgName, e);
				}
			}

			// Throw an error if projection could not be supported
			if (crs == null) {
				throw new SFException(
						"Failed to create projection for EPSG " + epsg
								+ ". Parameters: " + parameters + ". Name: "
								+ epsgName);
			}

			projection = new Projection(epsg, crs);

			projections.put(epsg, projection);
		}
		return projection;
	}

	/**
	 * Get the projection for the Spatial Reference System
	 * 
	 * @param srs
	 *            spatial reference system
	 * @return projection
	 * @since 1.1.8
	 */
	public static Projection getProjection(SpatialReferenceSystem srs) {

		long epsg = srs.getOrganizationCoordsysId();
		Projection projection = getProjection(epsg);

		return projection;
	}

}
