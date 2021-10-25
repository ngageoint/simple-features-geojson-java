package mil.nga.sf.geojson;

/**
 * Geometry Type enumeration
 * 
 * @author osbornb
 * @since 3.0.0
 */
public enum GeometryType {

	/**
	 * The root of the geometry type hierarchy
	 */
	GEOMETRY(mil.nga.sf.GeometryType.GEOMETRY, "Geometry"),

	/**
	 * A single location in space. Each point has an X and Y coordinate. A point
	 * MAY optionally also have a Z and/or an M value.
	 */
	POINT(mil.nga.sf.GeometryType.POINT, "Point"),

	/**
	 * A Curve that connects two or more points in space.
	 */
	LINESTRING(mil.nga.sf.GeometryType.LINESTRING, "LineString"),

	/**
	 * A restricted form of CurvePolygon where each ring is defined as a simple,
	 * closed LineString.
	 */
	POLYGON(mil.nga.sf.GeometryType.POLYGON, "Polygon"),

	/**
	 * A restricted form of GeometryCollection where each Geometry in the
	 * collection must be of type Point.
	 */
	MULTIPOINT(mil.nga.sf.GeometryType.MULTIPOINT, "MultiPoint"),

	/**
	 * A restricted form of MultiCurve where each Curve in the collection must
	 * be of type LineString.
	 */
	MULTILINESTRING(mil.nga.sf.GeometryType.MULTILINESTRING, "MultiLineString"),

	/**
	 * A restricted form of MultiSurface where each Surface in the collection
	 * must be of type Polygon.
	 */
	MULTIPOLYGON(mil.nga.sf.GeometryType.MULTIPOLYGON, "MultiPolygon"),

	/**
	 * A collection of zero or more Geometry instances.
	 */
	GEOMETRYCOLLECTION(mil.nga.sf.GeometryType.GEOMETRYCOLLECTION,
			"GeometryCollection");

	/**
	 * Simple Features Type
	 */
	private final mil.nga.sf.GeometryType type;

	/**
	 * Name
	 */
	private final String name;

	/**
	 * Constructor
	 * 
	 * @param type
	 *            simple features type
	 * @param name
	 *            name
	 */
	private GeometryType(mil.nga.sf.GeometryType type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * Get the simple features type
	 * 
	 * @return simple features type
	 */
	public mil.nga.sf.GeometryType getType() {
		return type;
	}

	/**
	 * Get the name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

}
