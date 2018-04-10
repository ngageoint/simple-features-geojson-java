package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

/**
 * Geometry
 * 
 * @author yutzlejp
 */
@JsonSubTypes({ @Type(Polygon.class), @Type(MultiPolygon.class),
		@Type(Point.class), @Type(MultiPoint.class),
		@Type(MultiLineString.class), @Type(LineString.class),
		@Type(GeometryCollection.class) })
@JsonIgnoreProperties({ "geometry" })
public abstract class Geometry extends GeoJsonObject {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Get the simple geometry
	 * 
	 * @return simple geometry
	 */
	public abstract mil.nga.sf.Geometry getGeometry();

}
