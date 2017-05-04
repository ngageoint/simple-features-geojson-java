package mil.nga.sf.projection;

/**
 * Projection constants
 * 
 * @author osbornb
 */
public class ProjectionConstants {

	/**
	 * Undefined Cartesian
	 */
	public static final int UNDEFINED_CARTESIAN = -1;

	/**
	 * Undefined Geographic
	 */
	public static final int UNDEFINED_GEOGRAPHIC = 0;

	/**
	 * EPSG world geodetic system
	 */
	public static final int EPSG_WORLD_GEODETIC_SYSTEM = 4326;

	/**
	 * EPSG code for web mercator
	 */
	public static final int EPSG_WEB_MERCATOR = 3857;

	/**
	 * EPSG code for world geodetic system geographical 3d
	 * 
	 * @since 1.2.1
	 */
	public static final int EPSG_WORLD_GEODETIC_SYSTEM_GEOGRAPHICAL_3D = 4979;

	/**
	 * Web Mercator Latitude Range (+ and -)
	 */
	public static final double WEB_MERCATOR_MAX_LAT_RANGE = 85.0511287798066;

	/**
	 * Web Mercator Latitude Range (+ and -)
	 */
	public static final double WEB_MERCATOR_MIN_LAT_RANGE = -85.05112877980659;

	/**
	 * Half the world distance in either direction
	 */
	public static double WEB_MERCATOR_HALF_WORLD_WIDTH = 20037508.342789244;

	/**
	 * Half the world longitude width for WGS84
	 * 
	 * @since 1.2.0
	 */
	public static double WGS84_HALF_WORLD_LON_WIDTH = 180.0;

	/**
	 * Half the world latitude height for WGS84
	 * 
	 * @since 1.2.0
	 */
	public static double WGS84_HALF_WORLD_LAT_HEIGHT = 90.0;

	/**
	 * Web mercator precision
	 */
	public static double WEB_MERCATOR_PRECISION = 0.0000000001;

}
