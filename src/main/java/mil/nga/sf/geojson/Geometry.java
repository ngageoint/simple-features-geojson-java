package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({ "type", "bbox" })
public abstract class Geometry extends GeoJsonObject {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Get the geometry type
	 * 
	 * @return geometry type
	 * @since 3.0.0
	 */
	@JsonIgnore
	public abstract GeometryType getGeometryType();

	/**
	 * Get the simple geometry
	 * 
	 * @return simple geometry
	 */
	@JsonIgnore
	public abstract mil.nga.sf.Geometry getGeometry();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getSimpleGeometry() {
		return getGeometry();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return getGeometryType().getName();
	}

}
